package com.example.vendo.Piush

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.vendo.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import java.security.Permission
import java.security.Permissions
import java.util.*


class HindiActivity : AppCompatActivity() {

    private lateinit var translateEnglishToHindi: FirebaseTranslator
    private lateinit var btn:Button
    private lateinit var edt:EditText
    private lateinit var txt:TextView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLanguage("hi")
        this.setContentView(R.layout.activity_hindi)
        btn = findViewById(R.id.button)
        edt = findViewById(R.id.edittext)
        txt = findViewById(R.id.textView3)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE

        btn.setOnClickListener {
           prepareTranslationalModel()
        }
    }

    private fun loadLanguage(s: String) {
        val locale = Locale(s)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    private fun prepareTranslationalModel() {
        txt.text = edt.text.toString()
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.INTERNET), 101);
            prepareTranslationalModel()
        }
        else{

            progressBar.visibility = View.VISIBLE
            val options: FirebaseTranslatorOptions = FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(FirebaseTranslateLanguage.EN)
                .setTargetLanguage(FirebaseTranslateLanguage.HI)
                .build()

            translateEnglishToHindi = FirebaseNaturalLanguage.getInstance().getTranslator(options)
            translateEnglishToHindi.downloadModelIfNeeded()
                .addOnSuccessListener {
                    translateText()
                    progressBar.background = resources.getDrawable(R.color.teal_200)
                }
//                .addOnFailureListener {
//                    txt.text = it.toString()
//                    progressBar.visibility = View.INVISIBLE
//                }
        }

    }

    private fun translateText() {
        val str = edt.text.toString()
        translateEnglishToHindi.translate(str)
            .addOnSuccessListener {
                txt.text = it!!
                progressBar.visibility = View.INVISIBLE
            }
            .addOnFailureListener {
                txt.text = it.toString()
                progressBar.visibility = View.INVISIBLE
            }
    }
}