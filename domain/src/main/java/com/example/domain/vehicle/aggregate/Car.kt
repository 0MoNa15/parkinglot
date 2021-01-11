package com.example.domain.vehicle.aggregate

import com.example.domain.vehicle.entity.LicensePlate

class Car(
    override val plateLicensePlate: LicensePlate,
    override var state: String,
    override var dateOfAdmission: String
): Vehicle(){
    companion object {
        const val COST_PER_HOUR = 1000
        const val COST_PER_DAY = 8000
    }
}