package com.example.domain.parkinglot.valueobjects

class Day {
    val name: String? = null
    val type: TypeOfDay? = null

    enum class TypeOfDay {
        NORMAL_DAY{
            val motorcycleCost = 4000
            val carCost = 8000
        },

        // Solo en estos dias pueden ingresar los vehiculos con placa que inicie en 'A'
        SPECIAL_DAYD{
            val motorcycleCost = 4000
            val carCost = 8000
        }
    }
}