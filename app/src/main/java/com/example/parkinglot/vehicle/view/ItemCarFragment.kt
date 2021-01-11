package com.example.parkinglot.vehicle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkinglot.R
import com.example.parkinglot.vehicle.model.CarContent

class ItemCarFragment : Fragment() {

    private lateinit var mAdapter: MyItemCarRecyclerViewAdapter
    lateinit var recyclerViewList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        initialiceWidget(view)
        return view
    }

    private fun initialiceWidget(view: View) {
        recyclerViewList = view.findViewById(R.id.recyclerViewListCar)

        mAdapter = MyItemCarRecyclerViewAdapter(activity!!, CarContent.ITEMS)
        mAdapter.setOnClickListener(View.OnClickListener {
            val position: Int = recyclerViewList.getChildAdapterPosition(it)
            Log.i("TEST", ""+ position)
            //CarContent.ITEMS[position]
        })

        recyclerViewList.layoutManager = LinearLayoutManager(context)
        recyclerViewList.adapter = mAdapter
    }
}