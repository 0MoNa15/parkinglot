package com.example.parkinglot.vehicle.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.parkinglot.R
import com.example.parkinglot.databinding.FragmentItemBinding
import com.example.parkinglot.generic.Utils

class VehicleViewAdapter(
    private val mContext: Context,
    private val values: List<Vehicle>
) : RecyclerView.Adapter<VehicleViewAdapter.ViewHolder>(), View.OnClickListener {

    private var mListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemBinding.root.setOnClickListener(this)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.addItem(item)
    }

    override fun getItemCount(): Int = values.size

    override fun onClick(view: View) {
        if (mListener != null) {
            mListener!!.onClick(view)
        }
    }

    fun setOnClickListener(listener: View.OnClickListener?) {
        mListener = listener
    }

    inner class ViewHolder(view: FragmentItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val titleTextView: TextView = view.root.findViewById(R.id.textViewTittle)
        private val licensePlateTextView: TextView = view.root.findViewById(R.id.textViewLicensePlateEdditable1)
        private val stateTextView: TextView = view.root.findViewById(R.id.textViewStateEdditable)
        private val dateTextView: TextView = view.root.findViewById(R.id.textViewDateEdditable)
        private val dateFixedTextView: TextView = view.root.findViewById(R.id.textViewDate)

        fun addItem(vehicle: Vehicle) {
            licensePlateTextView.text = String.format("%s%s%s", Utils.convertTextToUpperCase(vehicle.plateLicensePlate.id), " - ", vehicle.plateLicensePlate.city)
            titleTextView.text = if (vehicle is Car) mContext.getString(R.string.carro) else mContext.getString(R.string.moto)
            stateTextView.text = vehicle.state

            if(Utils.validateString(vehicle.dateOfAdmission)) {
                dateTextView.visibility = View.VISIBLE
                dateFixedTextView.visibility = View.VISIBLE
                dateTextView.text = vehicle.dateOfAdmission
            }
        }
    }
}