package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "parkinglot")
class ParkingLotEntity (
    @ColumnInfo(name = "daysoperation")
    val daysOfOperation: Int
)