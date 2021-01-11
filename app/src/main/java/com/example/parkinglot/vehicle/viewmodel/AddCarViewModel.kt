package com.example.parkinglot.vehicle.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkinglot.R
import com.example.parkinglot.generic.Utils

class AddCarViewModel() : ViewModel() {

    var licensePlateComplete = MutableLiveData<Float>()
    var message = MutableLiveData<String>()

    fun saveVehicle(licensePlate: String, carType: Boolean, motorcycleType: Boolean){
        if (!Utils.validateString(licensePlate)){
            //Mensaje
            //message.value = context.getString(R.string.se_necesita_la_placa)
            message.value = "se_necesita_la_placa"
            Log.i("TEST", "Placa vacía")
        } else if (carType){
            //Guardar carro
            Log.i("TEST", "Carro")
        } else if (motorcycleType){
            //Guardar moto
            Log.i("TEST", "Moto")
        }
    }

    fun validateLicensePlateComplete(licensePlate: String){
        if (Utils.validateString(licensePlate)){
            licensePlateComplete.value = 1F
        } else {
            licensePlateComplete.value = 0.5F
        }
    }
}