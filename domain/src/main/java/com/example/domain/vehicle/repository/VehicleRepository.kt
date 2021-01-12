package com.example.domain.vehicle.repository

import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle

/**
 * Obtener todos los tipos de vehiculos existentes
 */
interface VehicleRepository {
    fun getAllCars(): List<Car>
    fun getAllMotorcycle(): List<Motorcycle>
}