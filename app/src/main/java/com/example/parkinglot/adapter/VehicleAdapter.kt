package com.example.parkinglot.adapter

class VehicleAdapter(){
/*class VehicleAdapter(var listVehicle: List<Vehicle>, val context: Context) : RecyclerView.Adapter<VehicleAdapter.ViewHolderVehicle?>() {

    lateinit var listener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVehicle {
        val itemBinding = VehicleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderVehicle(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderVehicle, position: Int) {
        val vehicle: Vehicle = listVehicle[position]
        holder.bind(vehicle)
    }

    override fun getItemCount(): Int {
        return listVehicle.size
    }

    inner class ViewHolderVehicle(val itemBinding: VehicleListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(vehicle: Vehicle) {
            itemBinding.tvTipoVehiculo.text = if (vehicle is Car) "Carro" else "Moto"
            itemBinding.tvPlacaVehiculo.text = vehicle.plate
            itemBinding.tvDateIngreso.text = vehicle.entryDate.toString()
            itemBinding.btnRetirarVehiculo.setOnClickListener {
                showDialogRemove(vehicle, it.context)
            }
        }

        private fun showDialogRemove(vehicle: Vehicle, context: Context) {
            //AlertDialog
            val alert: AlertDialog.Builder = AlertDialog.Builder(context)
            val viewDialog =
                ItemVehicleBinding.inflate(LayoutInflater.from(context), itemBinding.root, false)
            alert.setView(viewDialog.root)

            val alertDialog: AlertDialog = alert.create()
            alertDialog.setCanceledOnTouchOutside(false)

            viewDialog.tvPriceService.text =
                (context as MainActivity).getPriceFinalService(vehicle).toString()
            viewDialog.tvPlateVehicle.text = vehicle.plate
            viewDialog.tvTypeVehicle.text = if (vehicle is Car) "Carro" else "Moto"
            viewDialog.tvEntryDate.text = vehicle.entryDate

            viewDialog.btnRemoveVehicle.setOnClickListener {
                if (vehicle is Car)
                    (context).startRequestRemoveVehicle(vehicle)
                else if (vehicle is Motorbike)
                    (context).startRequestRemoveMotorbike(vehicle)

                alertDialog.dismiss()
            }

            viewDialog.btnCancel.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }*/
}