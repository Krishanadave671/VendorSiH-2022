package com.example.vendo.Adapter

import android.app.Activity
import android.content.Context
import android.icu.lang.UCharacter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.Model.ChildparentModel
import com.example.vendo.Model.Myorderparentmodel
import com.example.vendo.R
import java.security.AccessControlContext

class myorderAdapterParent (private val list : ArrayList<Myorderparentmodel>,private val context: Context, private  val childlist : ArrayList<ChildparentModel>) : RecyclerView.Adapter<myorderAdapterParent.Viewholder>(){

    inner class Viewholder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val orderid : TextView = itemview.findViewById(R.id.orderid)
        val address : TextView  = itemview.findViewById(R.id.Address)
        val  duration : TextView = itemview.findViewById(R.id.duration)
        val ordertime : TextView = itemview.findViewById(R.id.ordertime)
        val orderprice : TextView = itemview.findViewById(R.id.orderprice)
        val childrecyclerview : RecyclerView = itemview.findViewById(R.id.child_recyclerview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.order_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val parentmodel = list.get(position)
        holder.orderid.setText(parentmodel.orderid)
        holder.address.setText(parentmodel.address)
        holder.duration.setText(parentmodel.duration)
        holder.ordertime.setText(parentmodel.ordertime)
        holder.orderprice.setText(parentmodel.orderprice)

        val childitemsAdapter = childitemsAdapter(childlist, context)
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        holder.childrecyclerview.layoutManager = linearLayoutManager
        holder.childrecyclerview.adapter = childitemsAdapter
    }

    override fun getItemCount(): Int = list.size
}