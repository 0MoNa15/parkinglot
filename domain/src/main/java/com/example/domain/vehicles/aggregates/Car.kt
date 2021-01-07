package com.example.domain.vehicles.aggregates

import com.example.domain.vehicles.entities.LicensePlate

class Car(
    val plateLicensePlate: LicensePlate,
    val model: String,
    val color: String
)