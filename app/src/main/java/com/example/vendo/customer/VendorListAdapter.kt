package com.example.vendo.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.R

class VendorListAdapter (private val vendorList : ArrayList<VendorList>):RecyclerView.Adapter<VendorListAdapter.VendorViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VendorListAdapter.VendorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.vendor_list,parent,false)
        return VendorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VendorListAdapter.VendorViewHolder, position: Int) {
        val curentItem = vendorList[position]

        holder.loc.text = curentItem.loc
        holder.ratings.text = curentItem.Rating.toString()
        holder.restaurant.text = curentItem.restaurant

    }

    override fun getItemCount(): Int {
        return vendorList.size
    }


class VendorViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var loc: TextView = itemView.findViewById(R.id.addressTextView)
    var restaurant : TextView = itemView.findViewById(R.id.NameTextView)
    var ratings : TextView = itemView.findViewById(R.id.ratingsTextView)

}



}