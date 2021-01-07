package com.example.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.infrastructure.database.dto.CarDao
import com.example.infrastructure.database.dto.MotorcycleDao
import com.example.infrastructure.database.entities.MotorcycleEntity
import com.example.infrastructure.database.entities.CarEntity

@Database(entities = [CarEntity::class, MotorcycleEntity::class], version = 3, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun carDao(): CarDao
    abstract fun motorbikeDao(): MotorcycleDao

    companion object {
        private var instanceManagerDatabase: AppDataBase? = null
        private val sLock = Any()
        fun getInstance(context: Context?): AppDataBase? {
            synchronized(sLock) {
                if (instanceManagerDatabase == null)
                    instanceManagerDatabase = Room.databaseBuilder(
                        context!!,
                        AppDataBase::class.java, "AppParkingLot"
                    )
                        .setJournalMode(JournalMode.TRUNCATE)
                        .build()
            }
            return instanceManagerDatabase
        }
    }
}