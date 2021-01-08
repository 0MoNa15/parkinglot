package com.example.domain.vehicles.repository

import com.example.domain.vehicles.aggregates.Car
import com.example.domain.vehicles.aggregates.Motorcycle

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