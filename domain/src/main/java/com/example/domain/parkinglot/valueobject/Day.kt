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
        fun availablesDays(): ArrayList<Day>{
            val listDays: ArrayList<Day> = ArrayList()

            val dayMonday = Day()
            dayMonday.identifyDay = "Monday"
            dayMonday.type = TypeOfDay.SPECIAL_DAYD
            listDays.add(dayMonday)

            val dayTuesday = Day()
            dayTuesday.identifyDay = "Tuesday"
            dayTuesday.type = TypeOfDay.NORMAL_DAY
            listDays.add(dayTuesday)

            val dayWednesday = Day()
            dayWednesday.identifyDay = "Wednesday"
            dayWednesday.type = TypeOfDay.NORMAL_DAY
            listDays.add(dayWednesday)

            val dayThursday = Day()
            dayThursday.identifyDay = "Thursday"
            dayThursday.type = TypeOfDay.NORMAL_DAY
            listDays.add(dayThursday)

            val dayFriday = Day()
            dayFriday.identifyDay = "Friday"
            dayFriday.type = TypeOfDay.NORMAL_DAY
            listDays.add(dayFriday)

            val daySaturday = Day()
            daySaturday.identifyDay = "Saturday"
            daySaturday.type = TypeOfDay.NORMAL_DAY
            listDays.add(daySaturday)

            val daySunday = Day()
            daySunday.identifyDay = "Sunday"
            daySunday.type = TypeOfDay.SPECIAL_DAYD
            listDays.add(daySunday)

            return listDays
        }
    }
}