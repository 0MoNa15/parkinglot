package com.example.domain.parkinglot.service

import androidx.lifecycle.MutableLiveData
import com.example.domain.parkinglot.entity.ParkingLot
import com.example.domain.parkinglot.valueobject.Day
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.aggregate.Vehicle.Companion.OUTSIDE_PARKING_LOT
import com.example.domain.vehicle.entity.LicensePlate
import com.example.domain.vehicle.repository.CarRepository
import com.example.domain.vehicle.repository.MotorcycleRepository
import com.example.domain.vehicle.repository.VehicleRepository
import com.example.domain.vehicle.service.CarService
import com.example.domain.vehicle.service.MotorcycleService
import com.example.domain.vehicle.service.VehicleService
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Validaciones y reglas de negocio con relación al parqueadero en general
 */
class ParkingLotService @Inject constructor(vehicleRepository: VehicleRepository, carRepository: CarRepository, motorcycleRepository: MotorcycleRepository) {
    var mVehicleService: VehicleService = VehicleService(vehicleRepository)
    var mCarService: CarService = CarService(carRepository)
    var mMotorcycleService: MotorcycleService = MotorcycleService(motorcycleRepository)

    companion object {
        fun carLimitValidation(currentQuantity: Int): Boolean{
            if (currentQuantity < ParkingLot.MAXIMUM_QUANTITY_CARS)
                return true
            return false
        }

        // 'A' en inicial de la placa solo ingresan los Domingos y Lunes
        fun licensePlateVerificationForAdmission(licensePlate: String): Boolean {
            val c: Calendar = Calendar.getInstance()
            val mDate = Date()
            c.set(mDate.year, mDate.month, mDate.day)
            val currentDay = c.get(Calendar.DAY_OF_WEEK);

            Day.availablesDays().forEach { day ->
                if (day.identifyDay == currentDay && day.type == Day.TypeOfDay.NORMAL_DAY) {
                    if (licensePlate[0].equals(LicensePlate.INITIAL_WITH_SPECIAL_CONDITION)) {
                            return false
                    }
                }
            }
            return true
        }

        fun motorcycleLimitValidation(currentQuantity: Int): Boolean{
            if (currentQuantity < ParkingLot.MAXIMUM_QUANTITY_MOTORCYCLES)
                return true
            return false
        }
    }

    fun getAllVehicles(): MutableLiveData<ArrayList<Vehicle>> {
        return mVehicleService.getAllVehicles()
    }

    fun saveMotorcycle(motorcycle: Motorcycle) {
        mMotorcycleService.saveMotorcycle(motorcycle)
    }

    fun saveCar(car: Car) {
        mCarService.saveCar(car)
    }

    fun calculateCostToPay(priceByHour: Int, priceByDay: Int, entryDateString: String): Int {
        var finalPrice = 0
        val entryDateInFormatDate: Date =
            SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).parse(entryDateString)!!
        val hoursInTheParking = convertTimeToHour(entryDateInFormatDate)

        when {
            hoursInTheParking < 9 -> {
                // Se cobra por hora cuando el tiempo en el parqueadero fué inferior a 9 horas
                finalPrice += hoursInTheParking * priceByHour
            }
            hoursInTheParking < 24 -> {
                // Se cobran un dia completo si el vehiculo estuvo por más de 9 horas hasta 24 horas
                finalPrice += hoursInTheParking * priceByDay
            }
            else -> {
                // Se cobra un dia completo por cada 24 horas transcurridas y
                // se cobra por horas el restante
                finalPrice += (hoursInTheParking / 24) * priceByDay
                finalPrice += (hoursInTheParking % 24) * priceByHour
            }
        }

        return finalPrice
    }

    private fun convertTimeToHour(entyDate: Date): Int {
        val currentDate = Date()
        val divisionInHours = 3600000
        return (currentDate.time - entyDate.time).toInt() / divisionInHours
    }

    fun exitToAVehicle(vehicle: Vehicle){
        // Cambiamos el estado del vehiculo
        vehicle.state = OUTSIDE_PARKING_LOT

        if (vehicle is Motorcycle) {
            mMotorcycleService.updateStatusMotorcycleOut(vehicle)
        } else if (vehicle is Car) {
            mCarService.updateStatusCarOut(vehicle)
        }

        // Calculamos el valor a pagar del vehiculo
        getCostToPay(vehicle)

        // Sumamos el monto obtenido al parqueadero
    }

    // Cuando se quiera sacar el vehiculo, obtendremos el costo final según el tiempo que haya estado allí
    fun getCostToPay(vehicle: Vehicle): Int {
        var priceFinal = 0
        val priceHour: Int
        val priceDay: Int

        if (vehicle is Motorcycle) {
            if (vehicle.cylinderCapacity > Motorcycle.SPECIAL_CYLINDER_CAPACITY_FROM)
                priceFinal += Motorcycle.ADITIONAL_COST_FOR_LARGER_CYLINDER_CAPACITY

            priceDay = Motorcycle.COST_PER_DAY_MOTORCYCLE
            priceHour = Motorcycle.COST_PER_HOUR_MOTORCYCLE
        } else {
            priceDay = Car.COST_PER_DAY
            priceHour = Car.COST_PER_HOUR
        }

        priceFinal += calculateCostToPay(
            priceDay,
            priceHour,
            vehicle.dateOfAdmission
        )
        return priceFinal
    }

    fun enterANewCar(car: Car): Boolean {
        return mCarService.enterANewCar(car)
    }

    fun enterANewMotorcycle(motorcycle: Motorcycle): Boolean{
        return mMotorcycleService.enterANewMotorcycle(motorcycle)
    }
}