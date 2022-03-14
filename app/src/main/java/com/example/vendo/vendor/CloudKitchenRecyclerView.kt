package com.example.vendo.vendor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.R
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CloudKitchenRecyclerView : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore
    private lateinit var cloudKitchenRecyclerView: RecyclerView
    private lateinit var kitchenArrayList: ArrayList<KitchensItem>
    private lateinit var kitcehnAdapter: CloudKitchenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cloud_kitchen_recycler_view)

        cloudKitchenRecyclerView = findViewById(R.id.cloudRecylerView)
        cloudKitchenRecyclerView.layoutManager = LinearLayoutManager(this)
        cloudKitchenRecyclerView.setHasFixedSize(true)

        kitchenArrayList = arrayListOf<KitchensItem>()
        kitcehnAdapter = CloudKitchenAdapter(kitchenArrayList)
        cloudKitchenRecyclerView.adapter = kitcehnAdapter

        EventChangeListner()


    }

    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("CloudKitchen").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
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