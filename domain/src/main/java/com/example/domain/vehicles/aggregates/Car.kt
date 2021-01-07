package com.example.domain.vehicles.aggregates

import com.example.domain.vehicles.entities.LicensePlate
import com.example.domain.vehicles.entities.Vehicle

class Car(
    override val plateLicensePlate: LicensePlate,
    override var model: String,
    override var color: String,
    override var state: String
): Vehicle()