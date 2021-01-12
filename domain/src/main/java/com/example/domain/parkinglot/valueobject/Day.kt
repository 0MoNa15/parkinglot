package com.example.domain.parkinglot.valueobject

import java.util.*
import kotlin.collections.ArrayList

class Day {
    var identifyDay: String? = null
    var type: TypeOfDay? = null

    enum class TypeOfDay {
        // Solo en los dias especiales pueden ingresar los vehiculos con placa que inicie en 'A'
        SPECIAL_DAYD,
        NORMAL_DAY
    }
    
    companion object {
        fun availablesDays(): List<Day>{
            val listDays: ArrayList<Day> = ArrayList()
            val day = Day()

            day.identifyDay = "Monday"
            day.type = TypeOfDay.SPECIAL_DAYD
            listDays.add(day)

            day.identifyDay = "Tuesday"
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = "Wednesday"
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = "Thursday"
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = "Friday"
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = "Saturday"
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = "Sunday"
            day.type = TypeOfDay.SPECIAL_DAYD
            listDays.add(day)

            return listDays
        }
    }
}