package com.example.domain.vehicles.repository

import com.example.domain.vehicles.aggregates.Car
import com.example.domain.vehicles.aggregates.Motorcycle

interface VehicleRepository {
    fun insertCar(carEntity: Car)
    fun getAllCars(): List<Car>
    fun getAmountCar(): Int

    fun getCylinderCapacityMotorcycle(id: Int): Int
    fun insertMotorcycle(carEntity: Motorcycle)
    fun getAllMotorcycle(): List<Motorcycle>
    fun getAmountMotorcycle(): Int
}