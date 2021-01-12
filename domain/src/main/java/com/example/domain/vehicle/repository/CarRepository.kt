package com.example.domain.vehicle.repository

import com.example.domain.vehicle.aggregate.Car

interface CarRepository {
    fun insertCar(car: Car)
    fun updateStatusCar(car: Car)
    fun getAmountCar(): Int
}