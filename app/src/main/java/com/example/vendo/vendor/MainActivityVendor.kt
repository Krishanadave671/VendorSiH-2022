package com.example.vendo.vendor

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vendo.R
import com.example.vendo.vendor.fragments.HomeFragmentVendor
import com.example.vendo.vendor.fragments.OrganizationFragmentVendor
import com.example.vendo.vendor.fragments.ProfileFragmentVendor
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem
import java.util.*

class MainActivityVendor : AppCompatActivity() {

    private var languageCode : String? = "en"
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigation: np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languageCode = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE)
            .getString("languageCode","en")
        loadLanguage()
        setContentView(R.layout.activity_main_vendor)

        bottomNavigation = findViewById(R.id.bottom_nav_vendor)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_vendor) as NavHostFragment
        navController = navHostFragment.navController

        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_organization,
                R.drawable.avd_organization
            ),
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home
            ),
            CbnMenuItem(
                R.drawable.ic_profile,
                R.drawable.avd_profile
            )
        )

        bottomNavigation.setMenuItems(menuItems,1)
        bottomNavigation.setOnMenuItemClickListener { cbnMenuItem, i ->
            val destFragment: Fragment = when ( cbnMenuItem.icon){
                R.drawable.ic_organization-> OrganizationFragmentVendor()
                R.drawable.ic_profile -> ProfileFragmentVendor()
                else -> HomeFragmentVendor()
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_vendor,destFragment).commit()
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