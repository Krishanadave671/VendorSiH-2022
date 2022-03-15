package com.example.vendo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.Model.ChildparentModel
import com.example.vendo.R

class childitemsAdapter(private val childlist : ArrayList<ChildparentModel>, private val context : Context) :
    RecyclerView.Adapter<childitemsAdapter.Viewholder>() {
    inner class Viewholder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val noofitems : TextView = itemview.findViewById(R.id.noofitems)
        val fooditem : TextView = itemview.findViewById(R.id.fooditem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.foodorderlayout, parent, false)
        return Viewholder(view)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val childmodel = childlist.get(position)
        holder.noofitems.setText(childmodel.noofitems.toString())
        holder.fooditem.setText(childmodel.item)
    }

    override fun getItemCount(): Int {
        return  childlist.size
    }
}
