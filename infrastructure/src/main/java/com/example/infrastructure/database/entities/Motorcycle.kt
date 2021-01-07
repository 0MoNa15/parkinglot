package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.vehicles.entities.LicensePlate

@Entity(tableName = "motorcycle")
class Motorcycle (
    @ColumnInfo(name = "licensePlate")
    var licensePlate: String,

    @ColumnInfo(name = "licensePlateCity")
    var licensePlateCity: String,

    @ColumnInfo(name = "model")
    var model: String,

    @ColumnInfo(name = "color")
    var color: String,

    @ColumnInfo(name = "cylinderCapacity")
    val cylinderCapacity: Int
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}