package com.example.vendo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.vendo.Tablayout.ViewpagerAdapter.ViewpagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TablayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)

        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        val viewpager2 = findViewById<ViewPager2>(R.id.viewpager)

        val adapter = ViewpagerAdapter(supportFragmentManager, lifecycle)

        viewpager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewpager2){
            tab, position -> when(position){
                0 -> {
                    tab.text = "First"
                }
                 1 -> {
                     tab.text = "Second"
                 }
                 2 -> {
                     tab.text = "Third "
                 }
            }
        }




    }
}