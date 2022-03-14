package com.example.vendo


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.vendo.admin.MainActivityAdmin
import com.example.vendo.customer.MainActivityCustomer
import com.example.vendo.vendor.MainActivityVendor
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import kotlin.system.exitProcess

class LogInActivity : AppCompatActivity() {

    private var languageCode : String? = "en"

    private lateinit var emailEditText : TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var customerLogIn: Button
    private lateinit var vendorLogIn: Button
    private lateinit var adminLogIn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        languageCode = this.getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE)
            .getString("languageCode","")
        (this.application as GlobalVariables).languageCode = languageCode
        loadLanguage()
        setContentView(R.layout.activity_log_in)

        emailEditText = findViewById(R.id.Email_login)
        passwordEditText = findViewById(R.id.Password_login)
        customerLogIn = findViewById(R.id.Button_customer)
        vendorLogIn = findViewById(R.id.Button_vendor)
        adminLogIn = findViewById(R.id.Button_admin)

        customerLogIn.setOnClickListener {
            startActivity(Intent(this,MainActivityCustomer::class.java))
        }
        vendorLogIn.setOnClickListener {
            Log.e("LC",languageCode.toString())
            startActivity(Intent(this,MainActivityVendor::class.java))
        }
        adminLogIn.setOnClickListener {
            startActivity(Intent(this, MainActivityAdmin::class.java))
        }

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
        this.finish()
        exitProcess(0)

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