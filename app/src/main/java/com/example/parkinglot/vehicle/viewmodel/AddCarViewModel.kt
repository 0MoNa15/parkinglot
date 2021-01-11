package com.example.parkinglot.vehicle.viewmodel

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.aggregate.Vehicle.Companion.OUTSIDE_PARKING_LOT
import com.example.domain.vehicle.aggregate.Vehicle.Companion.VEHICLE_NOT_PERMITTED
import com.example.domain.vehicle.aggregate.Vehicle.Companion.VEHICLE_NO_INSIDE_LIMITED
import com.example.domain.vehicle.aggregate.Vehicle.Companion.VEHICLE_OK
import com.example.domain.vehicle.entity.LicensePlate
import com.example.domain.vehicle.service.VehicleService
import com.example.parkinglot.generic.Utils

class AddCarViewModel@ViewModelInject constructor(var services: VehicleService) : ViewModel() {
    private var vehicles = MutableLiveData<ArrayList<Vehicle>>()
    var licensePlateComplete = MutableLiveData<Float>()
    var message = MutableLiveData<String>()
    var availableCylinderCapacity = MutableLiveData<Int>()

    fun saveVehicle(licensePlate: String, licensePlateCity:String, carType: Boolean, motorcycleType: Boolean, cylinderCapacity: String){
        if (!Utils.validateString(licensePlate)){
            //Mensaje
            //message.value = context.getString(R.string.se_necesita_la_placa)
            message.value = "se_necesita_la_placa"
            Log.i("TEST", "Placa vacía")
        } else if (carType){
            //Guardar carro
            Log.i("TEST", "Carro")
            enterANewCar(Car(LicensePlate(licensePlate, licensePlateCity), OUTSIDE_PARKING_LOT, ""))
        } else if (motorcycleType){
            //Guardar moto
            Log.i("TEST", "Moto")
            if (!Utils.validateString(cylinderCapacity)){
                message.value = "se_necesita_el_cilindraje"
            } else {
                enterANewMotorcycle(Motorcycle(LicensePlate(licensePlate, licensePlateCity), OUTSIDE_PARKING_LOT, "", cylinderCapacity.toInt()))
            }
        }
    }

    fun validateLicensePlateComplete(licensePlate: String){
        if (Utils.validateString(licensePlate)){
            licensePlateComplete.value = 1F
        } else {
            licensePlateComplete.value = 0.5F
        }
    }

    private fun enterANewMotorcycle(motorcycle: Motorcycle) {
        if (services.enterANewMotorcycle(motorcycle)) {
            vehicles.value?.add(motorcycle)
            message.value = "Moto Ok"
        } else {
            message.value = "no se ha podido agregar la moto"
        }
    }

    private fun enterANewCar(car: Car) {
        //when(services.enterANewCar(car)){
        when(services.saveCar(car)){
            VEHICLE_NO_INSIDE_LIMITED -> {
                message.value =  "Cupo superado en el parqueadero"
            }
            VEHICLE_NOT_PERMITTED -> {
                message.value =  "No está autorizado a ingresar"
            }
            VEHICLE_OK -> {
                vehicles.value?.add(car)
                message.value = "Carro OK"
            }
        }
    }

    fun validateMotorcycleType(isMotorcycle: Boolean){
        if (isMotorcycle) {
            availableCylinderCapacity.value = View.VISIBLE
        } else {
            availableCylinderCapacity.value = View.GONE
        }
    }
}