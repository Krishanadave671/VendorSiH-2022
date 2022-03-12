package com.example.vendo.vendor

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.vendo.R
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem
import java.util.*

class MainActivityVendor : AppCompatActivity() {

    private var languageCode : String? = "en"

    private lateinit var bottomNavigation: np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languageCode = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE)
            .getString("languageCode","en")
        loadLanguage()
        setContentView(R.layout.activity_main_vendor)

        bottomNavigation = findViewById(R.id.bottom_nav_vendor)
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home
            ),
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home
            )
        )

        bottomNavigation.setMenuItems(menuItems,0)
        bottomNavigation.setOnMenuItemClickListener { cbnMenuItem, i ->

        }
        bottomNavigation.setOnClickListener {

        }
    }

    private fun loadLanguage() {
        val locale = Locale(languageCode!!)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }
}