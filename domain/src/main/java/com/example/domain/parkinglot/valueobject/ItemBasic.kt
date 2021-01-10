package com.example.domain.parkinglot.valueobject

class ItemBasic {
    var id: Int = 0
    lateinit var title: String
    var image: Int = 0

    companion object {
        const val POSITION_INSIDE = 1
        const val POSITION_OUTSIDE = 2
        const val POSITION_CAR = 3
        const val POSITION_PAYMENT = 4
    }
}