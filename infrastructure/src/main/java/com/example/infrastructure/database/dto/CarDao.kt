package com.example.infrastructure.database.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.infrastructure.database.entities.CarEntity
import com.example.infrastructure.database.entities.MotorcycleEntity

@Dao
interface CarDao {
    @Insert
    fun insert(carEntity: CarEntity)

    @Update
    fun updateStatus(carEntity: CarEntity)

    @Query("SELECT * FROM car")
    fun getAll(): List<CarEntity>

    @Query("SELECT COUNT(*) FROM car")
    fun getAmount(): Int
}