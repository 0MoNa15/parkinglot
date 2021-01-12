package com.example.parkinglot.generic

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun validateString(text: String?): Boolean{
            return text != null && text.isNotEmpty()
        }

        fun convertDateInMilliseconds(): Long{
            val myDate = Date().toString()
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val date: Date = sdf.parse(myDate)
            return date.time
        }
    }
}