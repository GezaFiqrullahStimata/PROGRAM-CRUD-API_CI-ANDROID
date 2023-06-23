package com.example.myapplication.adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.DataItem


class DataAdapter(val data: List<DataItem>? , private val click: onClickItem) :
    RecyclerView.Adapter<DataAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.myapplication.R.layout.item_data,parent,false)
        return MyHolder(view)
    }
    override fun getItemCount() = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val v = holder.itemView
        val btnHapus = v.findViewById<Button>(com.example.myapplication.R.id.btnHapus) as Button

        holder.onBind(data?.get(position))
        holder.itemView.setOnClickListener() {
            click.clicked(data?.get(position))
        }
        btnHapus.setOnClickListener {
            click.delete(data?.get(position))
        }
    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val v = itemView
        val tvName = v.findViewById<TextView>(com.example.myapplication.R.id.tvName) as TextView
        val tvPhone = v.findViewById<TextView>(com.example.myapplication.R.id.tvPhone) as TextView
        val tvAddress = v.findViewById<TextView>(com.example.myapplication.R.id.tvAddress) as TextView

        fun onBind(get: DataItem?) {
            tvName.text = get?.staffName
            tvPhone.text = get?.staffHp
            tvAddress.text = get?.staffAlamat
        }
    }

    interface onClickItem{
        fun clicked (item: DataItem?)
        fun delete(item: DataItem?)
    }
}