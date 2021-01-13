package com.example.domain.parkinglot.service

import androidx.lifecycle.MutableLiveData
import com.example.domain.parkinglot.entity.ParkingLot
import com.example.domain.parkinglot.valueobject.Day
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.aggregate.Vehicle.Companion.OUTSIDE_PARKING_LOT
import com.example.domain.vehicle.entity.LicensePlate.Companion.INITIAL_WITH_SPECIAL_CONDITION
import com.example.domain.vehicle.entity.LicensePlate.Companion.INITIAL_WITH_SPECIAL_CONDITION_LOWER
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
            val sdf = SimpleDateFormat("EEEE")
            val d = Date()
            val dayOfTheWeek = sdf.format(d)

            var day: Day
            val mList: ArrayList<Day> = Day.availablesDays()

            for (i in 0 until Day.availablesDays().size) {
                day = mList[i]
                if (day.identifyDay == dayOfTheWeek && day.type == Day.TypeOfDay.NORMAL_DAY) {
                    if (licensePlate[0].equals(INITIAL_WITH_SPECIAL_CONDITION) ||
                        licensePlate[0].equals(INITIAL_WITH_SPECIAL_CONDITION_LOWER)) {
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
        val entryDateInFormatDate: Date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).parse(entryDateString)!!
        val hoursInTheParking = convertTimeToHour(entryDateInFormatDate)

        when {
            hoursInTheParking < 9 -> {
                // Se cobra por hora cuando el tiempo en el parqueadero fué inferior a 9 horas
                finalPrice += hoursInTheParking * priceByHour
            }
            hoursInTheParking < 24 -> {
                // Se cobran un dia completo si el vehiculo estuvo por más de 9 horas hasta 24 horas
                finalPrice += priceByDay
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

    fun exitToAVehicle(vehicle: Vehicle): Int{
        // Cambiamos el estado del vehiculo
        vehicle.state = OUTSIDE_PARKING_LOT

        if (vehicle is Motorcycle) {
            mMotorcycleService.updateStatusMotorcycleOut(vehicle)
        } else if (vehicle is Car) {
            mCarService.updateStatusCarOut(vehicle)
        }

        // Sumamos el monto obtenido a las ganancias del parqueadero

        // Calculamos el valor a pagar del vehiculo
        return getCostToPay(vehicle)

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
            priceHour,
            priceDay,
            vehicle.dateOfAdmission
        )
        return priceFinal
    }

    fun enterANewCar(car: Car): Boolean {
        car.dateOfAdmission = currentDate()
        return mCarService.enterANewCar(car)
    }

    fun enterANewMotorcycle(motorcycle: Motorcycle): Boolean{
        motorcycle.dateOfAdmission = currentDate()
        return mMotorcycleService.enterANewMotorcycle(motorcycle)
    }

    fun currentDate(): String{
        val stringPatterns = "dd/MM/yyyy HH:mm"
        val df = SimpleDateFormat(stringPatterns)
        val dateString = df.format(Date())
        return dateString
    }
}