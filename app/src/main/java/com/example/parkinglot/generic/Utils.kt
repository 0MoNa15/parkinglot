package com.example.parkinglot.generic

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun validateString(text: String?): Boolean{
            return text != null && text.isNotEmpty()
        }

        fun convertDateInMilliseconds(): Long{
            val date = Date()
            Log.i("TEST", "MyDate: $date")
            return date.time
        }

        fun convertTextToUpperCase(text: String): String{
            return text.toUpperCase()
        }
    }
}