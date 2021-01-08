package com.example.parkinglot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infrastructure.database.dto.CarDao
import com.example.infrastructure.database.dto.MotorcycleDao
import com.example.parkinglot.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var recyclerVehicle: RecyclerView
    private lateinit var bindingMainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiceComponents()
    }

    private fun initialiceComponents() {
        // Configuración
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        setContentView(view)

        // Lista
        /*recyclerVehicle = bindingMainActivity.recyclerViewSearchResults
        recyclerVehicle.layoutManager = GridLayoutManager(this@MainActivity.applicationContext)
        */

    }
}