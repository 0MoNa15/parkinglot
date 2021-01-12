package com.example.parkinglot.vehicle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.parkinglot.R
import com.example.parkinglot.databinding.FragmentItemListBinding
import com.example.parkinglot.vehicle.model.CarContent
import com.example.parkinglot.vehicle.viewmodel.VehicleViewModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleViewFragment : Fragment() {

    private lateinit var bindingCarFragment: FragmentItemListBinding
    private lateinit var mAdapter: VehicleViewAdapter
    private lateinit var mListRecyclerView: RecyclerView
    lateinit var addCarButton: Button
    lateinit var manager: FragmentManager
    lateinit var transaction: FragmentTransaction
    private lateinit var viewModel: VehicleViewModule

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCarFragment = FragmentItemListBinding.inflate(layoutInflater)
        initialiceWidget(bindingCarFragment.root)
        return bindingCarFragment.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[VehicleViewModule::class.java]
        observer()
    }

    private fun initialiceWidget(view: View) {
        //Declaraciones
        mListRecyclerView = view.findViewById(R.id.recyclerViewListCar)
        addCarButton = view.findViewById(R.id.buttonAddCar)
        manager = activity!!.supportFragmentManager
        transaction = manager.beginTransaction()

        //Lista
        mAdapter = VehicleViewAdapter(activity!!.applicationContext, CarContent.ITEMS)
        mAdapter.setOnClickListener(View.OnClickListener {
            val position: Int = mListRecyclerView.getChildAdapterPosition(it)
            Log.i("TEST", ""+ position)
            //CarContent.ITEMS[position]
        })

        mListRecyclerView.layoutManager = LinearLayoutManager(context)
        mListRecyclerView.adapter = mAdapter

        //Botón
        addCarButton.setOnClickListener {
            transaction.replace(R.id.frameLayoutContainer, AddVehicleFragment(), "")
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun observer() {
        viewModel.getVehiclesLiveData().observe(activity!!, Observer {
            showList(it!!)
        })
    }

    private fun showList(listVehicles: List<Vehicle>) {
        mAdapter = VehicleViewAdapter(activity!!.applicationContext, listVehicles)
        mAdapter.notifyDataSetChanged()
        mListRecyclerView.adapter = mAdapter
    }
}