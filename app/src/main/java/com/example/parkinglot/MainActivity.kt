package com.example.parkinglot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.parkinglot.valueobjects.ItemBasic
import com.example.parkinglot.adapters.GenericViewAdapter
import com.example.parkinglot.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var recyclerVehicle: RecyclerView
    lateinit var genericViewAdapter: GenericViewAdapter
    private lateinit var bindingMainActivity: ActivityMainBinding
    private val mList: ArrayList<ItemBasic> = ArrayList<ItemBasic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiceComponents()
    }

    private fun initialiceComponents() {
        // Creación de la lista
        createList()

        // Configuración
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        setContentView(view)

        // Lista
        genericViewAdapter = GenericViewAdapter(this@MainActivity.applicationContext, mList)
        recyclerVehicle = bindingMainActivity.recyclerViewList
        recyclerVehicle.adapter = genericViewAdapter

        val manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerVehicle.layoutManager = manager
    }

    private fun createList() {
        val itemBasicInside = ItemBasic()
        val itemBasicOutside = ItemBasic()
        val itemBasicVehicle = ItemBasic()
        val itemBasicMoney = ItemBasic()

        itemBasicInside.title = getString(R.string.ingreso)
        itemBasicInside.image = getDrawable(R.drawable.ic_enter_car_parking_lot)!!
        itemBasicOutside.title = getString(R.string.salida)
        itemBasicVehicle.title = getString(R.string.vehiculos)
        itemBasicMoney.title = "Recaudo"

        mList.add(itemBasicInside)
        mList.add(itemBasicOutside)
        mList.add(itemBasicVehicle)
        mList.add(itemBasicMoney)
    }
}