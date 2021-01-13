package com.example.parkinglot

import com.example.domain.parkinglot.service.ParkingLotService.Companion.licensePlateVerificationForAdmission
import com.example.domain.parkinglot.valueobject.Day
import com.example.domain.parkinglot.valueobject.Day.Companion.availablesDays
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class BusinessLogicRulesTest {
    //Plantilla
    /** Qué hará la prueba */
    @Test
    fun test(){
        // Arrange
        var correctData = false

        // Act
        correctData = true

        // Assert
        assertEquals(true, correctData)
    }


    /** Se deben tener dos dias especiales el Lunes y el Domingo */
    @Test
    fun testDaysList(){
        // Arrange
        val dayMonday = "Lunes"
        val daySunday = "Domingo"
        var correctData = false

        // Act
        availablesDays().forEach{
            if (it.identifyDay == dayMonday || it.identifyDay == daySunday){
                if (it.type == Day.TypeOfDay.SPECIAL_DAYD){
                    correctData = true
                }
            }
        }

        // Assert
        assertEquals(true, correctData)
    }

    /** Solo permitir que los vehiculos con placas iniciadas en 'A'
     * ingresen los dias Lunes y Domingo*/
    @Test
    fun restrictedLicensePlateInitialATest(){
        // Arrange
        val currentDay = SimpleDateFormat("EEEE").format(Date())
        val licensePlate = "ABC123"
        val dayMonday = "Lunes"
        val daySunday = "Domingo"
        var correctData = false

        // Act
        if (currentDay == dayMonday || currentDay == daySunday){
            // Si es un día especial se epera que se espera que permita el ingreso al parqueadero
            if (licensePlateVerificationForAdmission(licensePlate)) {
                correctData = true
            } 
        } else {
            // Si es un dia normal se epera que se espera NO DEJE ingresar al parqueadero
            if (!licensePlateVerificationForAdmission(licensePlate)) {
                correctData = true
            }         
        }
        
        // Assert
        assertEquals(true, correctData)
    }


}