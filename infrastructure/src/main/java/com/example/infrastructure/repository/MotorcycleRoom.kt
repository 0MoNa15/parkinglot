package com.example.infrastructure.repository

import android.content.Context
import android.os.AsyncTask
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.repository.MotorcycleRepository
import com.example.infrastructure.anticorruption.VehicleTranslator
import com.example.infrastructure.database.AppDataBase
import com.example.infrastructure.database.dao.MotorcycleDao
import com.example.infrastructure.database.entity.MotorcycleEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MotorcycleRoom @Inject constructor(@ApplicationContext val context: Context): MotorcycleRepository {
    private var appDataBase: AppDataBase? = null
    var mMotorcycleDao: MotorcycleDao? = null

    init {
        appDataBase = AppDataBase.getInstance(context)!!
        mMotorcycleDao = appDataBase?.motorbikeDao()
    }

    override fun insertMotorcycle(motorcycle: Motorcycle) {
        val motorcycleEntity = VehicleTranslator.fromModelToEntityMotorcycle(motorcycle)
        InsertMotorAsynkTask(mMotorcycleDao!!).execute(motorcycleEntity)
    }

    override fun updateStatusMotorcycle(motorcycle: Motorcycle){
        val motorcycleEntity = VehicleTranslator.fromModelToEntityMotorcycle(motorcycle)
        UpdateStateMotorcycleAsynkTask(mMotorcycleDao!!).execute(motorcycleEntity)
    }

    override fun getAmountMotorcycle(): Int {
        return CountMotorcycleAsynkTask(mMotorcycleDao!!).execute().get()
    }

    override fun getCylinderCapacityMotorcycle(id: Int): Int {
        return CylinderCapacityMotorcycleAsynkTask(mMotorcycleDao!!, id).execute().get()
    }

    class InsertMotorAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<MotorcycleEntity, Void, Void>() {
        override fun doInBackground(vararg params: MotorcycleEntity?): Void? {
            motorcycleDao.insert(params[0]!!)
            return null
        }
    }

    class UpdateStateMotorcycleAsynkTask(var motorcycleDao: MotorcycleDao) : AsyncTask<MotorcycleEntity, Void, Void>() {
        override fun doInBackground(vararg params: MotorcycleEntity?): Void? {
            motorcycleDao.updateStatus(params[0]!!)
            return null
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