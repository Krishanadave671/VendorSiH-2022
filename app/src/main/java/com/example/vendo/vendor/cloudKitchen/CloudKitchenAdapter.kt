package com.example.vendo.vendor.cloudKitchen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.R

class CloudKitchenAdapter(private val kitchenList : ArrayList<KitchensItem>) : RecyclerView.Adapter<CloudKitchenAdapter.CloudKitchenViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CloudKitchenViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cloud_kitchen_list,parent,false)
        return CloudKitchenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CloudKitchenViewHolder, position: Int) {
        val currentItem = kitchenList[position]

        holder.building.text = currentItem.Building
        holder.loc.text = currentItem.loc
        holder.noOfKitchens.text = currentItem.NoOfKitchens.toString()
        holder.street.text = currentItem.Street
    }

    override fun getItemCount(): Int {
        return kitchenList.size
    }

    class CloudKitchenViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var loc: TextView = itemView.findViewById(R.id.locTextView)
        var noOfKitchens: TextView = itemView.findViewById(R.id.noOfKitchensTextView)
        var street: TextView = itemView.findViewById(R.id.streetTextView)
        var building: TextView = itemView.findViewById(R.id.buildingTextView)
    }



}