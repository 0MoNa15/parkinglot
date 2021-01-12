package com.example.parkinglot.vehicle.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.parkinglot.databinding.FragmentItemListBinding
import com.example.parkinglot.vehicle.model.CarContent
import com.example.parkinglot.vehicle.viewmodel.VehicleViewModule
import dagger.hilt.android.AndroidEntryPoint
import com.example.parkinglot.R

@AndroidEntryPoint
class VehicleViewFragment : Fragment() {
    private lateinit var bindingCarFragment: FragmentItemListBinding
    private lateinit var transaction: FragmentTransaction
    private lateinit var mListRecyclerView: RecyclerView
    private lateinit var viewModel: VehicleViewModule
    private lateinit var mAdapter: VehicleViewAdapter
    private lateinit var manager: FragmentManager
    private lateinit var addCarButton: Button
    private lateinit var mViewType: String

    companion object {
        const val VIEW_TYPE = "view_type"
        const val INSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE = "inside_vehicle_parkinglot_view_type"
        const val OUTSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE = "outside_vehicle_parkinglot_view_type"
        const val LIST_VEHICLES_VIEW_TYPE = "list_vehicles_view_type"
    }

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
        mAdapter = VehicleViewAdapter(activity!!.applicationContext, CarContent.ITEMS)
        mListRecyclerView = view.findViewById(R.id.recyclerViewListCar)
        mListRecyclerView.layoutManager = LinearLayoutManager(context)
        addCarButton = view.findViewById(R.id.buttonAddCar)
        mViewType = arguments!!.getString(VIEW_TYPE)!!
        manager = activity!!.supportFragmentManager
        transaction = manager.beginTransaction()

        when(mViewType){
            LIST_VEHICLES_VIEW_TYPE -> {
                //Botón
                addCarButton.visibility = View.VISIBLE
                addCarButton.setOnClickListener {
                    transaction.replace(R.id.frameLayoutContainer, AddVehicleFragment(), "")
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
            INSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE -> {
                showToast(getString(R.string.si_no_encuentras_debes_agregar))
                addCarButton.visibility = View.GONE
                mAdapter.setOnClickListener(View.OnClickListener {
                    val position: Int = mListRecyclerView.getChildAdapterPosition(it)
                    showDialogConfirmationVehicleToParkingLot(CarContent.ITEMS[position])
                })
            }
            OUTSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE -> {
                addCarButton.visibility = View.GONE
                mAdapter.setOnClickListener(View.OnClickListener {
                    val position: Int = mListRecyclerView.getChildAdapterPosition(it)
                    showDialogConfirmationVehicleToParkingLot(CarContent.ITEMS[position])
                })
            }
        }

        mListRecyclerView.adapter = mAdapter
    }

    private fun observer() {
        viewModel.getVehiclesLiveData().observe(activity!!, Observer {
            showList(it!!)
        })
        viewModel.message.observe(activity!!, Observer {
            showToast(it)
        })
    }

    private fun showToast(it: String) {
        Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show()
    }

    private fun showList(listVehicles: List<Vehicle>) {
        listVehicles.forEach{
            CarContent.ITEMS.add(it)
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun showDialogConfirmationVehicleToParkingLot(vehicle: Vehicle){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        builder.setTitle(getString(R.string.ingreso))

        if (mViewType == INSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE){
            builder.setMessage(String.format("%s%s%s%s", getString(R.string.esta_seguro_guardar), " ", vehicle.plateLicensePlate.id, "?"))
        } else if(mViewType == OUTSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE){
            builder.setMessage(String.format("%s%s%s%s", getString(R.string.esta_seguro_eliminar), " ", vehicle.plateLicensePlate.id, "."))
        }

        val positiveText = getString(R.string.si)
        builder.setPositiveButton(positiveText) { dialog, _ ->
            if (mViewType == INSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE){
                viewModel.insideNewVehicle(vehicle)
                viewModel.message.value = getString(R.string.bienvenido)
                mAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else if(mViewType == OUTSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE){
                //viewModel.message.value = getString(R.string.salida_exitosa)
                viewModel.message.value = String.format("%s%s", getString(R.string.debe_pagar_suma_de), viewModel.exitToAVehicle(vehicle).toString())
                mAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        val negativeText = getString(R.string.cancelar)
        builder.setNegativeButton(negativeText) { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}