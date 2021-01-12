package com.example.domain.vehicle.service

import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.repository.CarRepository
import javax.inject.Inject

class CarService  @Inject constructor(var repository: CarRepository) {
    // Guardar por primera vez un carro
    fun saveCar(car: Car) {
        repository.insertCar(car)
    }

    // Para dar ingreso a un vehiculo al parqueadero
    fun enterANewCar(car: Car): Boolean{
        if (ParkingLotService.carLimitValidation(repository.getAmountCar()) && ParkingLotService.licensePlateVerificationForAdmission(car.plateLicensePlate.id)) {
            repository.updateStatusCar(changeStateInCar(car, Vehicle.INSIDE_PARKING_LOT))
            return true
        }
        return false
    }

    fun changeStateInCar(car: Car, state: String): Car {
        car.state = state
        return car
    }

    fun updateStatusCarOut(car: Car){
        repository.updateStatusCar(changeStateInCar(car, Vehicle.OUTSIDE_PARKING_LOT))
    }
}