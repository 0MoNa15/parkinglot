package com.example.domain.vehicle.service

import androidx.lifecycle.MutableLiveData
import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.parkinglot.service.ParkingLotService.Companion.carLimitValidation
import com.example.domain.parkinglot.service.ParkingLotService.Companion.licensePlateVerificationForAdmission
import com.example.domain.parkinglot.service.ParkingLotService.Companion.motorcycleLimitValidation
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Car.Companion.COST_PER_DAY
import com.example.domain.vehicle.aggregate.Car.Companion.COST_PER_HOUR
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Motorcycle.Companion.ADITIONAL_COST_FOR_LARGER_CYLINDER_CAPACITY
import com.example.domain.vehicle.aggregate.Motorcycle.Companion.COST_PER_DAY_MOTORCYCLE
import com.example.domain.vehicle.aggregate.Motorcycle.Companion.COST_PER_HOUR_MOTORCYCLE
import com.example.domain.vehicle.aggregate.Motorcycle.Companion.SPECIAL_CYLINDER_CAPACITY_FROM
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.aggregate.Vehicle.Companion.INSIDE_PARKING_LOT
import com.example.domain.vehicle.aggregate.Vehicle.Companion.OUTSIDE_PARKING_LOT
import com.example.domain.vehicle.repository.VehicleRepository
import javax.inject.Inject
import kotlin.collections.ArrayList

class VehicleService  @Inject constructor(var repository: VehicleRepository) {
    private val mParkingLotServices: ParkingLotService = ParkingLotService()

    fun getAllVehicles(): MutableLiveData<ArrayList<Vehicle>> {
        val listMotorcycle: List<Motorcycle> = repository.getAllMotorcycle()
        val listCar: List<Car> = repository.getAllCars()

        val arrayListVehicles: ArrayList<Vehicle> = ArrayList()

        listCar.forEach { car ->
            arrayListVehicles.add(car)
        }

        listMotorcycle.forEach { motorcycle ->
            arrayListVehicles.add(motorcycle)
        }

        val mutableLiveData: MutableLiveData<ArrayList<Vehicle>> = MutableLiveData()
        mutableLiveData.value = arrayListVehicles

        return mutableLiveData
    }

    // Guardar por primera vez un carro
    fun saveCar(car: Car) {
        repository.insertCar(car)
    }

    // Guardar por primera vez una moto
    fun saveMotorcycle(motorcycle: Motorcycle) {
        repository.insertMotorcycle(motorcycle)
    }

    // Para dar ingreso a un vehiculo al parqueadero
    fun enterANewCar(car: Car): Boolean{
        if (carLimitValidation(repository.getAmountCar()) && licensePlateVerificationForAdmission(car.plateLicensePlate.numberAndLetters)) {
            repository.updateStatusCar(changeStateInCar(car, INSIDE_PARKING_LOT))
            return true
        }
        return false
    }

    fun changeStateInCar(car: Car, state: String): Car {
        car.state = state
        return car
    }

    fun changeStateInMotorCycle(motorcycle: Motorcycle, state: String): Motorcycle {
        motorcycle.state = state
        return motorcycle
    }

    // Para dar ingreso a un vehiculo al parqueadero
    fun enterANewMotorcycle(motorcycle: Motorcycle): Boolean{
        if (motorcycleLimitValidation(repository.getAmountMotorcycle()) && licensePlateVerificationForAdmission(motorcycle.plateLicensePlate.numberAndLetters)) {
            repository.updateStatusMotorcycle(changeStateInMotorCycle(motorcycle, INSIDE_PARKING_LOT))
            return true
        }
        return false
    }

    fun exitToAVehicle(vehicle: Vehicle){
        // Cambiamos el estado del vehiculo
        vehicle.state = OUTSIDE_PARKING_LOT

        if (vehicle is Motorcycle) {
            repository.updateStatusMotorcycle(vehicle)
        } else if (vehicle is Car) {
            repository.updateStatusCar(vehicle)
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
            if (vehicle.cylinderCapacity > SPECIAL_CYLINDER_CAPACITY_FROM)
                priceFinal += ADITIONAL_COST_FOR_LARGER_CYLINDER_CAPACITY

            priceDay = COST_PER_DAY_MOTORCYCLE
            priceHour = COST_PER_HOUR_MOTORCYCLE
        } else {
            priceDay = COST_PER_DAY
            priceHour = COST_PER_HOUR
        }

        priceFinal += mParkingLotServices.calculateCostToPay(
            priceDay,
            priceHour,
            vehicle.dateOfAdmission
        )
        return priceFinal
    }
}