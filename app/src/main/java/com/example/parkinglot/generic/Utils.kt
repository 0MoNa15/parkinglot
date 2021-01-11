package com.example.parkinglot.generic

class Utils {
    companion object {
        fun validateString(text: String?): Boolean{
            return text != null && text.isNotEmpty()
        }
    }
}