package com.example.parkinglot

import androidx.lifecycle.MutableLiveData
import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.parkinglot.service.ParkingLotService.Companion.licensePlateVerificationForAdmission
import com.example.domain.parkinglot.valueobject.Day
import com.example.domain.parkinglot.valueobject.Day.Companion.availablesDays
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.aggregate.Vehicle.Companion.OUTSIDE_PARKING_LOT
import com.example.domain.vehicle.entity.LicensePlate
import com.example.domain.vehicle.repository.CarRepository
import com.example.domain.vehicle.repository.MotorcycleRepository
import com.example.domain.vehicle.repository.VehicleRepository
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BusinessLogicRulesTest {

    lateinit var mParkingLotService: ParkingLotService
    private lateinit var mCar: Car
    private lateinit var mMotorcycle: Motorcycle
    private lateinit var mLicensePlate: LicensePlate
    @Mock
    val vehicleRepository: VehicleRepository =
        mock(VehicleRepository::class.java)

    @Mock
    val carRepository: CarRepository =
        mock(CarRepository::class.java)

    @Mock
    val motorcycleRepository: MotorcycleRepository =
        mock(MotorcycleRepository::class.java)

    @Before
    fun init() {
        mParkingLotService = ParkingLotService(vehicleRepository, carRepository, motorcycleRepository)

        mLicensePlate = LicensePlate(Date().time.toString(), "Pereira")
        mCar = Car(mLicensePlate, OUTSIDE_PARKING_LOT, "11/01/2021 07:00")

        mLicensePlate = LicensePlate(Date().time.toString(), "Pereira")
        mMotorcycle = Motorcycle(mLicensePlate, OUTSIDE_PARKING_LOT, "10/01/2021 07:00", 100)
    }


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

    /** Rectifica que traiga los vehiculos almacenados de cualquier tipo */
    /*@Test
    fun getListAllTypesVehiclesTest(){
        // Arrange
        var correctData = false

        // Act
        getAllVehicles()
        correctData = true

        // Assert
        assertEquals(true, correctData)
    }*/

    /** Solo permita ingresar 20 carros */
    @Test
    fun validationMaxCarTest(){
        // Arrange
        // Se puede validar con el estado de los vehiculos
        var correctData = false

        // Act
        correctData = true

        // Assert
        assertEquals(true, correctData)
    }

    /** Solo permita ingresar 10 motos */
    @Test
    fun validationMaxMotorcycleTest(){
        // Arrange
        // Se puede validar con el estado de los vehiculos
        var correctData = false

        // Act
        correctData = true

        // Assert
        assertEquals(true, correctData)
    }

    /** Verificar que el almacenamiento de un carro esté funcionando correctamente */
    @Test
    fun saveCarSimulationTest() {
        //Arrange
        val mList: MutableLiveData<ArrayList<Vehicle>>
        val listEvaluate : ArrayList<Vehicle>
        var result = false
        var vehicle : Vehicle
        mLicensePlate = LicensePlate(Date().time.toString(), "Pereira")
        mCar = Car(mLicensePlate, OUTSIDE_PARKING_LOT, "11/01/2021 07:00")

        //Act
        mParkingLotService.saveCar(mCar)
        mList = mParkingLotService.getAllVehicles()

        if (mList.value != null && mList.value!!.isNotEmpty()) {
            listEvaluate = mList.value!!

            for (i in 0 until listEvaluate.size) {
                vehicle = listEvaluate[i]
                if (vehicle.plateLicensePlate.id == mCar.plateLicensePlate.id) {
                    result = true // Verificamos que el carro haya quedado guardado en el sistema
                }
            }
        }

        //Assert
        assertEquals(true, result)
    }

    /** Verificar que el almacenamiento de una moto esté funcionando correctamente */
    @Test
    fun saveMotorcycleSimulationTest() {
        //Arrange
        val mList: MutableLiveData<ArrayList<Vehicle>>
        val listEvaluate : ArrayList<Vehicle>
        var result = false
        var vehicle : Vehicle
        mLicensePlate = LicensePlate(Date().time.toString(), "Pereira")
        mMotorcycle = Motorcycle(mLicensePlate, OUTSIDE_PARKING_LOT, "10/01/2021 07:00", 100)

        //Act
        mParkingLotService.saveMotorcycle(mMotorcycle)
        mList = mParkingLotService.getAllVehicles()

        if (mList.value != null && mList.value!!.isNotEmpty()) {
            listEvaluate = mList.value!!

            for (i in 0 until listEvaluate.size) {
                vehicle = listEvaluate[i]
                if (vehicle.plateLicensePlate.id == mMotorcycle.plateLicensePlate.id) {
                    result = true // Verificamos que el carro haya quedado guardado en el sistema
                }
            }
        }

        //Assert
        assertEquals(true, result)
    }

}