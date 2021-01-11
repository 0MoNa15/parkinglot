package com.example.parkinglot.vehicle.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parkinglot.R
import com.example.parkinglot.databinding.AddCarFragmentBinding
import com.example.parkinglot.vehicle.viewmodel.AddCarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarFragment : Fragment() {
    private lateinit var viewModel: AddCarViewModel
    private lateinit var bindingAddCarFragment: AddCarFragmentBinding
    lateinit var savedVehicleButton: Button
    lateinit var licensePlateEditText: EditText
    lateinit var licensePlateCityEditText: EditText
    lateinit var cylinderCapacityEditText: EditText
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

    private fun observer() {
        viewModel.licensePlateComplete.observe(activity!!, Observer {
            savedVehicleButton.alpha = it!!
        })

        viewModel.message.observe(activity!!, Observer {
            Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show()
        })

        viewModel.availableCylinderCapacity.observe(activity!!, Observer {
            cylinderCapacityEditText.visibility = it
        })
    }

    private fun initialiceWidget(view: View) {
        licensePlateCityEditText = view.findViewById(R.id.editTextLicensePlateCity)
        motorcycleTypeSwitch = view.findViewById(R.id.switchMotorcycleType)
        licensePlateEditText = view.findViewById(R.id.editTextLicensePlate)
        cylinderCapacityEditText = view.findViewById(R.id.editTextCylinderCapacity)
        savedVehicleButton = view.findViewById(R.id.buttonAddCar)
        carTypeSwitch = view.findViewById(R.id.switchCarType)

        actionWidget()
    }

    private fun actionWidget() {
        savedVehicleButton.setOnClickListener {
            viewModel.saveVehicle(licensePlateEditText.text.toString(), licensePlateCityEditText.text.toString(), carTypeSwitch.isChecked, motorcycleTypeSwitch.isChecked, cylinderCapacityEditText.text.toString())
        }

        licensePlateEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.validateLicensePlateComplete(s?.toString()!!)
            }
        })

        motorcycleTypeSwitch.setOnCheckedChangeListener { _, isChecked ->
            run {
                viewModel.validateMotorcycleType(isChecked)
                carTypeSwitch.isChecked = !isChecked
            }
        }

        carTypeSwitch.setOnCheckedChangeListener { _, isChecked ->
            run {
                motorcycleTypeSwitch.isChecked = !isChecked
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddCarViewModel::class.java]
        observer()
    }
}