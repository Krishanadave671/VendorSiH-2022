package com.example.vendo.customer

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.R
import com.example.vendo.vendor.cloudKitchen.CloudKitchenAdapter
import com.example.vendo.vendor.cloudKitchen.KitchensItem
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream


class MainActivityCustomer : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore
    private lateinit var vendorRecyclerView: RecyclerView
    private lateinit var vendorListArray: ArrayList<VendorList>
    private lateinit var vendorListAdapter: VendorListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_customer)

        vendorRecyclerView = findViewById(R.id.vendorRecyclerView)
        vendorRecyclerView.layoutManager = LinearLayoutManager(this)
        vendorRecyclerView.setHasFixedSize(true)

        vendorListArray = arrayListOf<VendorList>()
        vendorListAdapter = VendorListAdapter(vendorListArray)
        vendorRecyclerView.adapter = vendorListAdapter

        EventChangeListner()

    }

    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("Vendors").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error != null)
                {
                    Log.e("FireStore Error",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){

                    if(dc.type == DocumentChange.Type.ADDED){

                        vendorListArray.add(dc.document.toObject(VendorList::class.java))
                    }
                }

                vendorListAdapter.notifyDataSetChanged()
            }


        })
    }
}