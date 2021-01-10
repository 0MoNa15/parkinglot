package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.infrastructure.database.entity.CarEntity

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