package com.example.domain.vehicle.aggregate

import com.example.domain.vehicle.entity.LicensePlate

abstract class Vehicle {
    // Para el estado del vehiculo
    companion object {
        val INSIDE_PARKING_LOT = "En parqueo"
        val OUTSIDE_PARKING_LOT = "Fuera del parqueo"
        val VEHICLE_OK = "vehiculo_agregado_correctamente"
        val VEHICLE_NO_INSIDE_LIMITED = "limitacion_por_cupo"
        val VEHICLE_NOT_PERMITTED = "no_autorizado_por_placa"
    }

    abstract val plateLicensePlate: LicensePlate
    abstract var state: String
    abstract var dateOfAdmission: String
}