package com.example.domain.parkinglot.valueobjects

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
            val listDays: List<Day> = ArrayList()

            listDays[0].identifyDay = Calendar.MONDAY
            listDays[0].type = TypeOfDay.SPECIAL_DAYD

            listDays[1].identifyDay = Calendar.TUESDAY
            listDays[1].type = TypeOfDay.NORMAL_DAY

            listDays[2].identifyDay = Calendar.WEDNESDAY
            listDays[2].type = TypeOfDay.NORMAL_DAY

            listDays[3].identifyDay = Calendar.THURSDAY
            listDays[3].type = TypeOfDay.NORMAL_DAY

            listDays[4].identifyDay = Calendar.FRIDAY
            listDays[4].type = TypeOfDay.NORMAL_DAY

            listDays[5].identifyDay = Calendar.SATURDAY
            listDays[5].type = TypeOfDay.NORMAL_DAY

            listDays[6].identifyDay = Calendar.SUNDAY
            listDays[6].type = TypeOfDay.SPECIAL_DAYD

            return listDays
        }
    }
}