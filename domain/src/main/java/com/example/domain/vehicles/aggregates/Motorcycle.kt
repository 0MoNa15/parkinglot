package com.example.domain.vehicles.aggregates

import com.example.domain.vehicles.entities.LicensePlate
import com.example.domain.vehicles.entities.Vehicle

class Motorcycle(
    override val plateLicensePlate: LicensePlate,
    override var model: String,
    override var color: String,
    override var state: String,
    val cylinderCapacity: Int
): Vehicle()