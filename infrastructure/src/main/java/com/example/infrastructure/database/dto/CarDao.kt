package com.example.infrastructure.database.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructure.database.entities.CarEntity

@Dao
interface CarDao {
    @Insert
    fun insert(carEntity: CarEntity)

    @Query("SELECT * FROM car")
    fun getAll(): List<CarEntity>

    @Query("SELECT COUNT(*) FROM car")
    fun getAmount(): Int
}