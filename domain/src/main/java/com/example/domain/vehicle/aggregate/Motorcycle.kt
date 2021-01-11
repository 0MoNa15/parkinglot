package com.example.domain.vehicle.aggregate

import com.example.domain.vehicle.entity.LicensePlate

class Motorcycle(
    override val plateLicensePlate: LicensePlate,
    override var state: String,
    override var dateOfAdmission: String,
    val cylinderCapacity: Int
): Vehicle(){
    companion object {
        const val COST_PER_HOUR_MOTORCYCLE = 500
        const val COST_PER_DAY_MOTORCYCLE = 4000
        const val ADITIONAL_COST_FOR_LARGER_CYLINDER_CAPACITY = 200
        const val SPECIAL_CYLINDER_CAPACITY_FROM = 500
    }
}