package com.example.domain.vehicle.service

import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.repository.CarRepository
import com.example.domain.vehicle.repository.MotorcycleRepository
import javax.inject.Inject

class MotorcycleService  @Inject constructor(var repository: MotorcycleRepository) {
    private val mParkingLotServices: ParkingLotService = ParkingLotService()
    // Guardar por primera vez una moto
    fun saveMotorcycle(motorcycle: Motorcycle) {
        repository.insertMotorcycle(motorcycle)
    }

    fun changeStateInMotorCycle(motorcycle: Motorcycle, state: String): Motorcycle {
        motorcycle.state = state
        return motorcycle
    }

    // Para dar ingreso a un vehiculo al parqueadero
    fun enterANewMotorcycle(motorcycle: Motorcycle): Boolean{
        if (mParkingLotServices.motorcycleLimitValidation(repository.getAmountMotorcycle()) && mParkingLotServices.licensePlateVerificationForAdmission(
                motorcycle.plateLicensePlate.id
            )
        ) {
            repository.updateStatusMotorcycle(changeStateInMotorCycle(motorcycle,
                Vehicle.INSIDE_PARKING_LOT
            ))
            return true
        }
        return false
    }
}