package com.example.domain.parkinglot.entity

import com.example.domain.parkinglot.valueobject.Day
import com.example.domain.parkinglot.valueobject.Payment

class ParkingLot {
    val daysOfOperation: List<Day>? = null
    val payment: Payment? = null

    companion object {
        const val MAXIMUM_QUANTITY_MOTORCYCLES = 10
        const val MAXIMUM_QUANTITY_CARS = 20
    }
}