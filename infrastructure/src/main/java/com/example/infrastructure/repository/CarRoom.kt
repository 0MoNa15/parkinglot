package com.example.infrastructure.repository

import android.content.Context
import android.os.AsyncTask
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.repository.CarRepository
import com.example.infrastructure.anticorruption.VehicleTranslator
import com.example.infrastructure.database.AppDataBase
import com.example.infrastructure.database.dao.CarDao
import com.example.infrastructure.database.dao.MotorcycleDao
import com.example.infrastructure.database.entity.CarEntity
import com.example.infrastructure.database.entity.MotorcycleEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CarRoom @Inject constructor(@ApplicationContext val context: Context): CarRepository {
    private var appDataBase: AppDataBase? = null
    var mCarDao: CarDao? = null

    init {
        appDataBase = AppDataBase.getInstance(context)!!
        mCarDao = appDataBase?.carDao()
    }

    override fun getAllCars(): List<Car> {
        val array = SelectCarAsynkTask(mCarDao!!).execute().get()
        return VehicleTranslator.fromListCarEntityToListCarModel(array!!)
    }

    override fun insertCar(car: Car) {
        val carEntity = VehicleTranslator.fromModelToEntityCar(car)
        InsertCarAsynkTask(mCarDao!!).execute(carEntity)
    }

    override fun updateStatusCar(car: Car){
        val carEntity = VehicleTranslator.fromModelToEntityCar(car)
        UpdateStatCarAsynkTask(mCarDao!!).execute(carEntity)
    }
    
    override fun getAmountCar(): Int {
        return CountCarAsynkTask(mCarDao!!).execute().get()
    }

    override fun getOnlyCarsEnteredParkingLot(): List<Car> {
        val array = SelectCarEnteredAsynkTask(mCarDao!!).execute().get()
        return VehicleTranslator.fromListCarEntityToListCarModel(array!!)
    }

    class InsertCarAsynkTask(var carDao: CarDao) : AsyncTask<CarEntity, Void, Void>() {
        override fun doInBackground(vararg params: CarEntity?): Void? {
            carDao.insert(params[0]!!)
            return null
        }
    }

    class UpdateStatCarAsynkTask(var carDao: CarDao) : AsyncTask<CarEntity, Void, Void>() {
        override fun doInBackground(vararg params: CarEntity?): Void? {
            carDao.updateStatus(params[0]!!)
            return null
        }
    }

    class CountCarAsynkTask(var carDao: CarDao) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void): Int {
            return carDao.getAmount()
        }
    }

    class SelectCarAsynkTask(var carDao: CarDao) : AsyncTask<Void, Void, List<CarEntity>>() {
        override fun doInBackground(vararg params: Void?): List<CarEntity> {
            return carDao.getAll()
        }
    }

    class SelectCarEnteredAsynkTask(var carDao: CarDao) : AsyncTask<Void, Void, List<CarEntity>>() {
        override fun doInBackground(vararg params: Void?): List<CarEntity> {
            return carDao.getEntered()
        }
    }
}