package com.example.parkinglot.parkinglot.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.parkinglot.parkinglot.model.ItemBasic
import com.example.parkinglot.databinding.ItemBinding

class GenericViewAdapter (
    private val mContext: Context,
    private var mList: List<ItemBasic>
) : RecyclerView.Adapter<GenericViewAdapter.ViewHolder?>(), View.OnClickListener {
    private var mListener: View.OnClickListener? = null

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemBinding.root.setOnClickListener(this)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        val itemBasic: ItemBasic = mList[position]
        holder.addItemBasic(itemBasic)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onClick(view: View) {
        if (mListener != null) {
            mListener!!.onClick(view)
        }
    }

    fun setOnClickListener(listener: View.OnClickListener?) {
        mListener = listener
    }

    inner class ViewHolder(mView: ItemBinding) : RecyclerView.ViewHolder(mView.root) {
        var mTitleTextView: TextView = mView.textViewTitle
        var mImageView: ImageView = mView.imageView

        fun addItemBasic(itemBasic: ItemBasic) {
            mTitleTextView.text = itemBasic.title
            mImageView.setImageResource(itemBasic.image)
        }
    }
}