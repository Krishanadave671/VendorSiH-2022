package com.example.vendo

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.Boolean


class LanguageActivity : AppCompatActivity() {
    var prevStarted = "prevStarted"
    override fun onResume() {
        super.onResume()
        val sharedpreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, Boolean.TRUE)
            editor.apply()
        } else {
            startActivity(Intent(this,LogInActivity::class.java))
            finish()
        }
    }


    private lateinit var hindiBtn: Button
    private lateinit var EnglishBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        hindiBtn = findViewById(R.id.hindi)
        EnglishBtn = findViewById(R.id.english)


        val appSharedPrefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        val prefsEditor = appSharedPrefs.edit();

        hindiBtn.setOnClickListener {
            prefsEditor.putString("languageCode","hi")
            prefsEditor.apply()
            start()
        }
        EnglishBtn.setOnClickListener {
            prefsEditor.putString("languageCode","en")
            prefsEditor.apply()
            start()
        }

    }

    private fun start() {
        startActivity(Intent(this,LogInActivity::class.java))
    }
}