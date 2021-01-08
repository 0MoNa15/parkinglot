package com.example.parkinglot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.infrastructure.database.dto.CarDao
import com.example.infrastructure.database.dto.MotorcycleDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}