package com.example.vendo.vendor.fragments

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.vendo.GlobalVariables
import com.example.vendo.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import java.util.*


class ProfileFragmentVendor : Fragment() , OnMapReadyCallback {

    private var languageCode : String? = "en"
    private lateinit var translateEnglishToHindi: FirebaseTranslator
    private lateinit var vendorName: TextView
    private lateinit var vendorInfo: TextView
    private lateinit var vendorLicenseInfo: TextView
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        languageCode = (activity?.application as GlobalVariables).languageCode
        loadLanguage()
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile_vendor, container, false)
        vendorName = view.findViewById(R.id.vendor_name)
        vendorInfo = view.findViewById(R.id.vendor_info)
        vendorLicenseInfo = view.findViewById(R.id.vendor_license_info)
        mapFragment =childFragmentManager.findFragmentById(R.id.vendor_location_info) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if(languageCode=="hi"){
            prepareTranslationalModel()
        }


        return view
    }

    private fun loadLanguage() {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity?.baseContext?.resources?.updateConfiguration(
            config,
            activity?.baseContext?.resources?.displayMetrics
        )
    }

    private fun prepareTranslationalModel() {
        if(context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.INTERNET) } != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context as Activity,  arrayOf(Manifest.permission.INTERNET), 101);
            prepareTranslationalModel()
        }
        else{
            val options: FirebaseTranslatorOptions = FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(FirebaseTranslateLanguage.EN)
                .setTargetLanguage(FirebaseTranslateLanguage.HI)
                .build()

            translateEnglishToHindi = FirebaseNaturalLanguage.getInstance().getTranslator(options)
            translateEnglishToHindi.downloadModelIfNeeded()
                .addOnSuccessListener {
                    translateText()
                }
                .addOnFailureListener {
                    Log.e("LCt",it.message.toString())
                }
        }

    }

    private fun translateText() {
        var str = vendorName.text.toString()
        translateEnglishToHindi.translate(str)
            .addOnSuccessListener {
                vendorName.text = it
            }
            .addOnFailureListener {
                Log.e("LCt1",it.message.toString())
            }

        str = vendorLicenseInfo.text.toString()
        translateEnglishToHindi.translate(str)
            .addOnSuccessListener {
                vendorLicenseInfo.text = it
            }
            .addOnFailureListener {
                Log.e("LCt1",it.message.toString())
            }

        str = vendorInfo.text.toString()
        translateEnglishToHindi.translate(str)
            .addOnSuccessListener {
                vendorInfo.text = it
            }
            .addOnFailureListener {
                Log.e("LCt1",it.message.toString())
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val vadapav = LatLng(19.020345,72.832103)
        mMap.addMarker(MarkerOptions().position(vadapav).title("Kirti college ka vadapav"))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    vadapav.latitude,
                    vadapav.longitude
                ), 16.0f
            )
        )
    }

}

