package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.vehicles.entities.LicensePlate

@Entity(tableName = "car")
class CarEntity (
    //  @ColumnInfo nombre de la columna de la tabla en la base de datos
    @ColumnInfo(name = "licensePlate")
    var licensePlate: String,

    @ColumnInfo(name = "licensePlateCity")
    var licensePlateCity: String,

    @ColumnInfo(name = "model")
    var model: String,

    @ColumnInfo(name = "color")
    var color: String,

    @ColumnInfo(name = "state")
    var state: String,

    @ColumnInfo(name = "dateadmission")
    var dateOfAdmission: String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}