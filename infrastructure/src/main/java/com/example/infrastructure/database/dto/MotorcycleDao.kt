package com.example.infrastructure.database.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructure.database.entities.Car
import com.example.infrastructure.database.entities.Motorcycle

@Dao
interface MotorcycleDao {
    @Insert
    fun insert(carEntity: Motorcycle)

    @Query("SELECT * FROM motorcycle")
    fun getAll(): List<Motorcycle>

    @Query("SELECT COUNT(*) FROM motorcycle")
    fun getAmount(): Int

    @Query("SELECT cylinderCapacity FROM motorcycle WHERE id = :mIdMotorcycle")
    fun getCylinderCapacity(mIdMotorcycle: Int): Int
}