package com.example.infrastructure.database.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructure.database.entities.MotorcycleEntity

@Dao
interface MotorcycleDao {
    @Insert
    fun insert(carEntity: MotorcycleEntity)

    @Query("SELECT * FROM motorcycle")
    fun getAll(): List<MotorcycleEntity>

    @Query("SELECT COUNT(*) FROM motorcycle")
    fun getAmount(): Int

    @Query("SELECT cylinderCapacity FROM motorcycle WHERE id = :mIdMotorcycle")
    fun getCylinderCapacity(mIdMotorcycle: Int): Int
}