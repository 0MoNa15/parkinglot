package com.example.parkinglot.vehicle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import com.example.parkinglot.R
import com.example.parkinglot.databinding.AddCarFragmentBinding
import com.example.parkinglot.vehicle.viewmodel.AddCarViewModel

class AddCarFragment : Fragment() {
    private lateinit var viewModel: AddCarViewModel
    private lateinit var bindingAddCarFragment: AddCarFragmentBinding
    lateinit var savedVehicleButton: Button
    lateinit var licensePlateEditText: EditText
    lateinit var carTypeSwitch: Switch
    lateinit var motorcycleTypeSwitch: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingAddCarFragment = AddCarFragmentBinding.inflate(layoutInflater)
        initialiceWidget(bindingAddCarFragment.root)
        return bindingAddCarFragment.root
    }

    private fun initialiceWidget(view: View) {
        carTypeSwitch = view.findViewById(R.id.switchCarType)
        motorcycleTypeSwitch = view.findViewById(R.id.switchMotorcycleType)
        licensePlateEditText = view.findViewById(R.id.editTextLicensePlate)
        savedVehicleButton = view.findViewById(R.id.buttonAddCar)
        savedVehicleButton.setOnClickListener {
            savedVehicleButton.alpha = 1.0F
            //savedVehicleButton.alpha = 0.5F
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddCarViewModel::class.java]
    }
}