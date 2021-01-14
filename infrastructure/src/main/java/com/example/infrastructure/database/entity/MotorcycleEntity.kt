package com.example.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycle")
class MotorcycleEntity (
    @ColumnInfo(name = "licenseplate")
    var licensePlate: String,

    @ColumnInfo(name = "licenseplatecity")
    var licensePlateCity: String,

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