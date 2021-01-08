package com.example.infrastructure.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "payments")
class PaymentEntity (
    @ColumnInfo(name = "amont")
    val amont: Int,

    @ColumnInfo(name = "date")
    val date: String
)