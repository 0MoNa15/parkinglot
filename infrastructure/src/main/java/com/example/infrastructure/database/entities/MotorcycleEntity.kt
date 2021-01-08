package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.vehicles.entities.LicensePlate

@Entity(tableName = "motorcycle")
class MotorcycleEntity (
    @ColumnInfo(name = "licenseplate")
    var licensePlate: String,

    @ColumnInfo(name = "licenseplatecity")
    var licensePlateCity: String,

    @ColumnInfo(name = "model")
    var model: String,

    @ColumnInfo(name = "color")
    var color: String,

    @ColumnInfo(name = "state")
    var state: String,

    @ColumnInfo(name = "dateadmission")
    var dateOfAdmission: String,

    @ColumnInfo(name = "cylindercapacity")
    val cylinderCapacity: Int
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}