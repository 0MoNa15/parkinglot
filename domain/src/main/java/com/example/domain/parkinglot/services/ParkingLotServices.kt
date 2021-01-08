package com.example.domain.parkinglot.services

import com.example.domain.parkinglot.entities.ParkingLot
import com.example.domain.parkinglot.valueobjects.Day
import com.example.domain.vehicles.entities.LicensePlate
import java.text.SimpleDateFormat
import java.util.*

class ParkingLotServices {
    // Funciona 24/7
    // Valor dia, hora
    // Dinero ingresado
    // cantidad max

    companion object{
        // 'A' en inicial de la placa solo ingresan los Domingos y Lunes
        fun licensePlateVerificationForAdmission(licensePlate: String): Boolean {
            val currentDay = Calendar.DAY_OF_WEEK

            Day.availablesDays().forEach { day ->
                if (licensePlate[0].equals(LicensePlate.INITIAL_WITH_SPECIAL_CONDITION) &&
                    day.identifyDay == currentDay &&
                    day.type == Day.TypeOfDay.SPECIAL_DAYD){
                    return true
                }
            }

            return false
        }

        fun carLimitValidation(currentQuantity: Int): Boolean{
            if (currentQuantity < ParkingLot.MAXIMUM_QUANTITY_CARS)
                return true
            return false
        }

        fun motorcycleLimitValidation(currentQuantity: Int): Boolean{
            if (currentQuantity < ParkingLot.MAXIMUM_QUANTITY_MOTORCYCLES)
                return true
            return false
        }
    }

    fun calculateCostToPay(priceByHour: Int, priceByDay: Int, entryDateString: String): Int {
        var finalPrice = 0
        val entryDateInFormatDate: Date =
            SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).parse(entryDateString)!!
        val hoursInTheParking = convertTimeToHour(entryDateInFormatDate)

        when {
            hoursInTheParking < 9 -> {
                // Se cobra por hora cuando el tiempo en el parqueadero fué inferior a 9 horas
                finalPrice += hoursInTheParking * priceByHour
            }
            hoursInTheParking < 24 -> {
                // Se cobran un dia completo si el vehiculo estuvo por más de 9 horas hasta 24 horas
                finalPrice += hoursInTheParking * priceByDay
            }
            else -> {
                // Se cobra un dia completo por cada 24 horas transcurridas y
                // se cobra por horas el restante
                finalPrice += (hoursInTheParking / 24) * priceByDay
                finalPrice += (hoursInTheParking % 24) * priceByHour
            }
        }

        return finalPrice
    }

    private fun convertTimeToHour(entyDate: Date): Int {
        val currentDate = Date()
        val divisionInHours = 3600000
        return (currentDate.time - entyDate.time).toInt() / divisionInHours
    }
}