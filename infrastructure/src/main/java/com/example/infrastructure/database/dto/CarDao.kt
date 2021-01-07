package com.example.infrastructure.database.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructure.database.entities.Car

@Dao
interface CarDao {
    @Insert
    fun insert(carEntity: Car)

    @Query("SELECT * FROM car")
    fun getAll(): List<Car>

    @Query("SELECT COUNT(*) FROM car")
    fun getAmount(): Int
}