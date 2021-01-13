package com.example.domain.vehicle.service

import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.repository.MotorcycleRepository
import javax.inject.Inject

class MotorcycleService  @Inject constructor(var repository: MotorcycleRepository) {
    fun getAllVehicles(): ArrayList<Vehicle> {
        val listMotorcycle: List<Motorcycle> = repository.getAllMotorcycle()
        val arrayListVehicles: ArrayList<Vehicle> = ArrayList()

        listMotorcycle.forEach { motorcycle ->
            arrayListVehicles.add(motorcycle)
        }

        return arrayListVehicles
    }

    fun getOnlyVehiclesEnteredParkingLot(): ArrayList<Vehicle> {
        val listMotorcycle: List<Motorcycle> = repository.getOnlyMotorcyclesEnteredParkingLot()
        val arrayListVehicles: ArrayList<Vehicle> = ArrayList()

        listMotorcycle.forEach { motorcycle ->
            arrayListVehicles.add(motorcycle)
        }

        return arrayListVehicles
    }
    // Guardar por primera vez una moto
    fun saveMotorcycle(motorcycle: Motorcycle) {
        repository.insertMotorcycle(motorcycle)
    }

    // Para dar ingreso a un vehiculo al parqueadero
    fun enterANewMotorcycle(motorcycle: Motorcycle): Boolean{
        if (ParkingLotService.motorcycleLimitValidation(repository.getAmountMotorcycle()) && ParkingLotService.licensePlateVerificationForAdmission(motorcycle.plateLicensePlate.id)) {
            repository.updateStatusMotorcycle(changeStateInMotorCycle(motorcycle, Vehicle.INSIDE_PARKING_LOT))
            return true
        }
        return false
    }

    fun changeStateInMotorCycle(motorcycle: Motorcycle, state: String): Motorcycle {
        motorcycle.state = state
        return motorcycle
    }

    fun updateStatusMotorcycleOut(motorcycle: Motorcycle){
        repository.updateStatusMotorcycle(changeStateInMotorCycle(motorcycle, Vehicle.OUTSIDE_PARKING_LOT))
    }
}