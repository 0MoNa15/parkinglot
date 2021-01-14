package com.example.domain.vehicle.repository

import com.example.domain.vehicle.aggregate.Motorcycle

interface MotorcycleRepository {
    fun getAllMotorcycle(): List<Motorcycle>
    fun getOnlyMotorcyclesEnteredParkingLot(): List<Motorcycle>
    fun insertMotorcycle(motorcycle: Motorcycle)
    fun updateStatusMotorcycle(motorcycle: Motorcycle)
    fun getAmountMotorcycle(): Int
    fun getCylinderCapacityMotorcycle(id: Int): Int
}