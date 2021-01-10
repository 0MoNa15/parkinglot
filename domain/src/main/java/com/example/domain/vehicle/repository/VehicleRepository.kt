package com.example.domain.vehicle.repository

import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle

interface VehicleRepository {
    fun insertCar(car: Car)
    fun updateStatusCar(car: Car)
    fun getAllCars(): List<Car>
    fun getAmountCar(): Int
    fun getCylinderCapacityMotorcycle(id: Int): Int

    fun insertMotorcycle(motorcycle: Motorcycle)
    fun updateStatusMotorcycle(motorcycle: Motorcycle)
    fun getAllMotorcycle(): List<Motorcycle>
    fun getAmountMotorcycle(): Int
}