package com.example.parkinglot.vehicle.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parkinglot.R
import com.example.parkinglot.databinding.AddCarFragmentBinding
import com.example.parkinglot.vehicle.viewmodel.AddCarViewModel

class AddCarFragment : Fragment() {
    private lateinit var viewModel: AddCarViewModel
    private lateinit var bindingAddCarFragment: AddCarFragmentBinding
    lateinit var savedVehicleButton: Button
    lateinit var licensePlateEditText: EditText
    lateinit var licensePlateCityEditText: EditText
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
    }

    private fun initialiceWidget(view: View) {
        licensePlateCityEditText = view.findViewById(R.id.editTextLicensePlateCity)
        motorcycleTypeSwitch = view.findViewById(R.id.switchMotorcycleType)
        licensePlateEditText = view.findViewById(R.id.editTextLicensePlate)
        savedVehicleButton = view.findViewById(R.id.buttonAddCar)
        carTypeSwitch = view.findViewById(R.id.switchCarType)

        actionWidget()
    }

    private fun actionWidget() {
        savedVehicleButton.setOnClickListener {
            viewModel.saveVehicle(licensePlateEditText.text.toString(), carTypeSwitch.isChecked, motorcycleTypeSwitch.isChecked)
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val mAddCarViewModel = AddCarViewModel(activity!!)
        viewModel = ViewModelProvider(this)[AddCarViewModel::class.java]
        observer()
    }
}