package com.example.domain.parkinglot.valueobject

import java.util.*
import kotlin.collections.ArrayList

class Day {
    var identifyDay: Int? = null
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

            day.identifyDay = Calendar.MONDAY
            day.type = TypeOfDay.SPECIAL_DAYD
            listDays.add(day)

            day.identifyDay = Calendar.TUESDAY
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = Calendar.WEDNESDAY
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = Calendar.THURSDAY
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = Calendar.FRIDAY
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = Calendar.SATURDAY
            day.type = TypeOfDay.NORMAL_DAY
            listDays.add(day)

            day.identifyDay = Calendar.SUNDAY
            day.type = TypeOfDay.SPECIAL_DAYD
            listDays.add(day)

            return listDays
        }
    }
}