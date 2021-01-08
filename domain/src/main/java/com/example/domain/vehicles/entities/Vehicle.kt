package com.example.domain.vehicles.entities

abstract class Vehicle {
    // Para el estado del vehiculo
    companion object {
        val INSIDE_PARKING_LOT = "inside_the_parking_lot"
        val OUTSIDE_PARKING_LOT = "outside_the_parking_lot"
    }

    abstract val plateLicensePlate: LicensePlate
    abstract var model: String
    abstract var color: String
    abstract var state: String
}