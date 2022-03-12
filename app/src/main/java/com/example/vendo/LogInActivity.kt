package com.example.vendo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class LogInActivity : AppCompatActivity() {

    private var languageCode : String? = "en"

    private lateinit var emailEditText : TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var loginButton : Button
    private lateinit var signuptextview : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languageCode = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE)
            .getString("languageCode","en")
        loadLanguage()
        setContentView(R.layout.activity_log_in)

        emailEditText = findViewById(R.id.Email_login)
        passwordEditText = findViewById(R.id.Password_login)




    }
    fun loginuser() {
        val email = emailEditText.editText?.text.toString()
        val password = passwordEditText.editText?.text.toString()

    }

    fun checkloggedinstate() {
//        if(auth.currentUser == null) { // not logged in
//            Toast.makeText(this, "You are not logged in ", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(this, "You are  logged in ", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun onStart() {
        super.onStart()
        checkloggedinstate()
    }



    private fun loadLanguage() {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }
}