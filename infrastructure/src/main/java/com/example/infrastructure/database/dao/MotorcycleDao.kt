package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.infrastructure.database.entity.CarEntity
import com.example.infrastructure.database.entity.MotorcycleEntity

@Dao
interface MotorcycleDao {
    @Insert
    fun insert(motorcycleEntity: MotorcycleEntity)

    @Update
    fun updateStatus(motorcycleEntity: MotorcycleEntity)

    @Query("SELECT * FROM motorcycle")
    fun getAll(): List<MotorcycleEntity>

    @Query("SELECT * FROM motorcycle WHERE state = 'En parqueo'")
    fun getEntered(): List<MotorcycleEntity>

    @Query("SELECT COUNT(*) FROM motorcycle")
    fun getAmount(): Int

    @Query("SELECT cylinderCapacity FROM motorcycle WHERE id = :mIdMotorcycle")
    fun getCylinderCapacity(mIdMotorcycle: Int): Int
}