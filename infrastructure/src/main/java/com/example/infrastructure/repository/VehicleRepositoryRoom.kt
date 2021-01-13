package com.example.infrastructure.repository

import android.content.Context
import android.os.AsyncTask
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.repository.VehicleRepository
import com.example.infrastructure.anticorruption.VehicleTranslator
import com.example.infrastructure.database.AppDataBase
import com.example.infrastructure.database.dao.CarDao
import com.example.infrastructure.database.dao.MotorcycleDao
import com.example.infrastructure.database.entity.CarEntity
import com.example.infrastructure.database.entity.MotorcycleEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class VehicleRepositoryRoom @Inject constructor(@ApplicationContext val context: Context): VehicleRepository {
    private var appDataBase: AppDataBase? = null
    var mCarDao: CarDao? = null
    var mMotorcycleDao: MotorcycleDao? = null

    init {
        appDataBase = AppDataBase.getInstance(context)!!
        mMotorcycleDao = appDataBase?.motorbikeDao()
        mCarDao = appDataBase?.carDao()
    }

    override fun getAllCars(): List<Car> {
        val array = SelectCarAsynkTask(mCarDao!!).execute().get()
        return VehicleTranslator.fromListCarEntityToListCarModel(array!!)
    }

    override fun getAllMotorcycle(): List<Motorcycle> {
        val array = SelectMotorcycleAsynkTask(mMotorcycleDao!!).execute().get()
        return VehicleTranslator.fromListMotorcycleEntityToListMotorcycleModel(array!!)
    }

    override fun getOnlyCarsEnteredParkingLot(): List<Car> {
        val array = SelectCarEnteredAsynkTask(mCarDao!!).execute().get()
        return VehicleTranslator.fromListCarEntityToListCarModel(array!!)
    }

    override fun getOnlyMotorcyclesEnteredParkingLot(): List<Motorcycle> {
        val array = SelectMotorcycleEnteredAsynkTask(mMotorcycleDao!!).execute().get()
        return VehicleTranslator.fromListMotorcycleEntityToListMotorcycleModel(array!!)
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

    class SelectMotorcycleAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<Void, Void, List<MotorcycleEntity>>() {
        override fun doInBackground(vararg params: Void?): List<MotorcycleEntity> {
            return motorcycleDao.getAll()
        }
    }

    class SelectMotorcycleEnteredAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<Void, Void, List<MotorcycleEntity>>() {
        override fun doInBackground(vararg params: Void?): List<MotorcycleEntity> {
            return motorcycleDao.getEntered()
        }
    }
}