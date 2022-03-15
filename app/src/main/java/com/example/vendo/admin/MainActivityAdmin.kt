package com.example.vendo.admin

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.vendo.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivityAdmin : AppCompatActivity() {

    val db = Firebase.firestore
    lateinit var building : EditText
    lateinit var street : EditText
    lateinit var loc : EditText
    lateinit var noOfKitchen : EditText
    lateinit var submit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)

        building = findViewById(R.id.buildingEditText)
        street = findViewById(R.id.streetEditText)
        loc= findViewById(R.id.locEditText)
        noOfKitchen = findViewById(R.id.noOfKitchenEditText)
        submit = findViewById(R.id.submit)



        submit.setOnClickListener {
            addData(
                building.text.toString(),
                street.text.toString(),
                noOfKitchen.text.toString().toInt(),
                loc.text.toString(),
            )
        }
    }

    fun addData(Building : String, Street : String, NoOfKitchen : Int,loc : String){
        val kitchen = hashMapOf(
            "Building" to Building,
            "Street" to Street,
            "NoOfKitchens" to NoOfKitchen,
            "loc" to loc,
        )

        db.collection("CloudKitchen")
            .add(kitchen)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}