package com.example.domain.vehicle.service

import androidx.lifecycle.MutableLiveData
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.repository.VehicleRepository
import javax.inject.Inject
import kotlin.collections.ArrayList

class VehicleService  @Inject constructor(var repository: VehicleRepository) {
    fun getAllVehicles(): MutableLiveData<ArrayList<Vehicle>> {
        val listMotorcycle: List<Motorcycle> = repository.getAllMotorcycle()
        val listCar: List<Car> = repository.getAllCars()

        val arrayListVehicles: ArrayList<Vehicle> = ArrayList()

        listCar.forEach { car ->
            arrayListVehicles.add(car)
        }

        listMotorcycle.forEach { motorcycle ->
            arrayListVehicles.add(motorcycle)
        }

        val mutableLiveData: MutableLiveData<ArrayList<Vehicle>> = MutableLiveData()
        mutableLiveData.value = arrayListVehicles

        return mutableLiveData
    }

    fun getOnlyVehiclesEnteredParkingLot(): MutableLiveData<ArrayList<Vehicle>> {
        val listMotorcycle: List<Motorcycle> = repository.getOnlyMotorcyclesEnteredParkingLot()
        val listCar: List<Car> = repository.getOnlyCarsEnteredParkingLot()

        val arrayListVehicles: ArrayList<Vehicle> = ArrayList()

        listCar.forEach { car ->
            arrayListVehicles.add(car)
        }

        listMotorcycle.forEach { motorcycle ->
            arrayListVehicles.add(motorcycle)
        }

        val mutableLiveData: MutableLiveData<ArrayList<Vehicle>> = MutableLiveData()
        mutableLiveData.value = arrayListVehicles

        return mutableLiveData
    }
}