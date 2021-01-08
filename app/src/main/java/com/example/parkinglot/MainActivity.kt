package com.example.parkinglot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infrastructure.database.dto.CarDao
import com.example.infrastructure.database.dto.MotorcycleDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var recyclerVehicle: RecyclerView
    //private lateinit var bindingMainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        */
        setContentView(R.layout.activity_main)
        initialiceComponents()
    }

    private fun initialiceComponents() {

        //RecyclerView
        /*recyclerVehicle = bindingMainActivity.recyclerViewSearchResults
        recyclerVehicle.layoutManager = GridLayoutManager(this@MainActivity.applicationContext)
        */

    }
}