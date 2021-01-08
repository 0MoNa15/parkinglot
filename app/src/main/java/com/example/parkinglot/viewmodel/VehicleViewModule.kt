package com.example.parkinglot.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.vehicles.aggregates.Car
import com.example.domain.vehicles.aggregates.Motorcycle
import com.example.domain.vehicles.entities.Vehicle
import com.example.domain.vehicles.services.VehicleServices

class VehicleViewModule @ViewModelInject constructor(var services: VehicleServices): ViewModel(){
    private var vehicles = MutableLiveData<ArrayList<Vehicle>>()

    init {
        getVehicles()
    }

    private fun getVehicles() {
        vehicles = services.getAllVehicles()
    }

    fun getVehiclesLiveData(): MutableLiveData<ArrayList<Vehicle>> {
        return vehicles
    }

    fun saveCar(car: Car){
        vehicles.value?.add(car)
    }

    fun saveMotorcycle(motorcycle: Motorcycle){
        vehicles.value?.add(motorcycle)
    }

    fun enterANewMotorcycle(motorcycle: Motorcycle): Boolean {
        return if (services.enterANewMotorcycle(motorcycle)) {
            vehicles.value?.add(motorcycle)
            true
        } else {
            false
        }
    }

    fun enterANewCar(car: Car): Boolean {
        return if (services.enterANewCar(car)) {
            vehicles.value?.add(car)
            true
        } else {
            false
        }
    }

    fun getCostToPay(vehicle: Vehicle): Int {
        return services.getCostToPay(vehicle)
    }
}