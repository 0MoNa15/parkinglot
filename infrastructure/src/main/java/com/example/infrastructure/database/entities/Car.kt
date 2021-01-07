package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.vehicles.entities.LicensePlate

@Entity(tableName = "car")
class Car (
    //  @ColumnInfo nombre de la columna de la tabla en la base de datos
    @ColumnInfo(name = "licensePlate")
    var plateLicensePlate: LicensePlate,

    @ColumnInfo(name = "model")
    var model: String,

    @ColumnInfo(name = "color")
    var color: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}