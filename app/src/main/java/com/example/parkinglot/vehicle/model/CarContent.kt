package com.example.parkinglot.vehicle.model

import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.entity.LicensePlate
import java.util.ArrayList

object CarContent {
    val ITEMS: MutableList<Vehicle> = ArrayList()

    init {
        for (i in 1..ITEMS.size) {
            if (ITEMS[i] is Car) {
                addItem(
                    Car(
                        LicensePlate(ITEMS[i].plateLicensePlate.numberAndLetters, ITEMS[i].plateLicensePlate.city),
                        ITEMS[i].model,
                        ITEMS[i].color,
                        ITEMS[i].state,
                        ITEMS[i].dateOfAdmission
                    )
                )
            } else if(ITEMS[i] is Motorcycle) {
                addItem(Motorcycle(
                    LicensePlate(ITEMS[i].plateLicensePlate.numberAndLetters, ITEMS[i].plateLicensePlate.city),
                    ITEMS[i].model,
                    ITEMS[i].color,
                    ITEMS[i].state,
                    ITEMS[i].dateOfAdmission,
                    122
                ))
            }
        }
    }

    private fun addItem(item: Vehicle) {
        ITEMS.add(item)
    }
}