package com.example.domain.vehicles.entities

abstract class Vehicle {
    // Para el estado del vehiculo
    val INSIDE_PARKING_LOT = "inside_the_parking_lot"
    val OUTSIDE_PARKING_LOT = "outside_the_parking_lot"

    val plateLicensePlate: LicensePlate? = null
    val model: String? = null
    val color: String? = null
    val state: String? = null
}