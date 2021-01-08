package com.example.domain.vehicles.aggregates

import com.example.domain.vehicles.entities.LicensePlate
import com.example.domain.vehicles.entities.Vehicle

class Car(
    override val plateLicensePlate: LicensePlate,
    override var model: String,
    override var color: String,
    override var state: String
): Vehicle(){
    companion object {
        const val COST_PER_HOUR = 1000
        const val COST_PER_DAY = 8000
    }
}