package com.example.vendo.vendor.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.R
import com.example.vendo.vendor.cloudKitchen.CloudKitchenAdapter
import com.example.vendo.vendor.cloudKitchen.KitchensItem
import com.google.firebase.firestore.*

class OrganizationFragmentVendor : Fragment() {

    private lateinit var db : FirebaseFirestore
    private lateinit var cloudKitchenRecyclerView: RecyclerView
    private lateinit var kitchenArrayList: ArrayList<KitchensItem>
    private lateinit var kitcehnAdapter: CloudKitchenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_organization_vendor, container, false)

        cloudKitchenRecyclerView = view.findViewById(R.id.cloudRecyclerView)
        cloudKitchenRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cloudKitchenRecyclerView.setHasFixedSize(true)

        kitchenArrayList = arrayListOf<KitchensItem>()
        kitcehnAdapter = CloudKitchenAdapter(kitchenArrayList)
        cloudKitchenRecyclerView.adapter = kitcehnAdapter

        EventChangeListner()

        return view


    }

    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("CloudKitchen").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error != null)
                {
                    Log.e("FireStore Error",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){

                    if(dc.type == DocumentChange.Type.ADDED){

                        kitchenArrayList.add(dc.document.toObject(KitchensItem::class.java))
                    }
                }

                kitcehnAdapter.notifyDataSetChanged()
            }


        })
    }

}