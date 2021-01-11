package com.example.domain.vehicle.entity

class LicensePlate (
    val id: String,
    val city: String
){
    companion object {
        const val INITIAL_WITH_SPECIAL_CONDITION = "A"
    }
}