package com.example.domain.vehicles.entities

class LicensePlate (
    val numberAndLetters: String,
    val city: String
){
    companion object {
        const val INITIAL_WITH_SPECIAL_CONDITION = "A"
    }
}