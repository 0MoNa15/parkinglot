package com.example.infrastructure.repository

import android.content.Context
import android.os.AsyncTask
import com.example.domain.vehicles.aggregates.Car
import com.example.domain.vehicles.aggregates.Motorcycle
import com.example.domain.vehicles.repository.VehicleRepository
import com.example.infrastructure.anticorruption.VehicleTranslator
import com.example.infrastructure.database.AppDataBase
import com.example.infrastructure.database.dto.CarDao
import com.example.infrastructure.database.dto.MotorcycleDao
import com.example.infrastructure.database.entities.CarEntity
import com.example.infrastructure.database.entities.MotorcycleEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class VehicleImplementation @Inject constructor(@ApplicationContext val context: Context):
    VehicleRepository {

    private var appDataBase: AppDataBase? = null
    var mCarDao: CarDao? = null
    var mMotorcycleDao: MotorcycleDao? = null

    init {
        appDataBase = AppDataBase.getInstance(context)!!
        mMotorcycleDao = appDataBase?.motorbikeDao()
        mCarDao = appDataBase?.carDao()
    }

    override fun insertCar(car: Car) {
        val carEntity = VehicleTranslator.fromModelToEntityCar(car)
        InsertCarAsynkTask(mCarDao!!).execute(carEntity)
    }

    override fun getAllCars(): List<Car> {
        val array = SelectCarAsynkTask(mCarDao!!).execute().get()
        return VehicleTranslator.fromListCarEntityToListCarModel(array!!)
    }

    override fun getAmountCar(): Int {
        return CountCarAsynkTask(mCarDao!!).execute().get()
    }

    override fun insertMotorcycle(motorcycle: Motorcycle) {
        val motorcycleEntity = VehicleTranslator.fromModelToEntityMotorcycle(motorcycle)
        InsertMotorAsynkTask(mMotorcycleDao!!).execute(motorcycleEntity)
    }

    override fun getAllMotorcycle(): List<Motorcycle> {
        val array = SelectMotorcycleAsynkTask(mMotorcycleDao!!).execute().get()
        return VehicleTranslator.fromListMotorcycleEntityToListMotorcycleModel(array!!)
    }

    override fun getAmountMotorcycle(): Int {
        return CountMotorcycleAsynkTask(mMotorcycleDao!!).execute().get()
    }

    override fun getCylinderCapacityMotorcycle(id: Int): Int {
        return CylinderCapacityMotorcycleAsynkTask(mMotorcycleDao!!, id).execute().get()
    }

    class InsertCarAsynkTask(var carDao: CarDao) : AsyncTask<CarEntity, Void, Void>() {
        override fun doInBackground(vararg params: CarEntity?): Void? {
            carDao.insert(params[0]!!)
            return null
        }
    }

    class SelectCarAsynkTask(var carDao: CarDao) : AsyncTask<Void, Void, List<CarEntity>>() {
        override fun doInBackground(vararg params: Void?): List<CarEntity> {
            return carDao.getAll()
        }
    }

    class CountCarAsynkTask(var carDao: CarDao) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void): Int {
            return carDao.getAmount()
        }
    }

    class InsertMotorAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<MotorcycleEntity, Void, Void>() {
        override fun doInBackground(vararg params: MotorcycleEntity?): Void? {
            motorcycleDao.insert(params[0]!!)
            return null
        }
    }

    class SelectMotorcycleAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<Void, Void, List<MotorcycleEntity>>() {
        override fun doInBackground(vararg params: Void?): List<MotorcycleEntity> {
            return motorcycleDao.getAll()
        }
    }

    class CountMotorcycleAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void): Int {
            return motorcycleDao.getAmount()
        }
    }

    class CylinderCapacityMotorcycleAsynkTask(var motorcycleDao: MotorcycleDao, var id: Int) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void): Int {
            return motorcycleDao.getCylinderCapacity(id)
        }
    }
}