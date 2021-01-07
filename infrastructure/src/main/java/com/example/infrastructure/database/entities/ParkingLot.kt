package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.domain.parkinglot.valueobjects.Payment

@Entity(tableName = "parkinglot")
class ParkingLot (
    @ColumnInfo(name = "daysoperation")
    val daysOfOperation: Int
)