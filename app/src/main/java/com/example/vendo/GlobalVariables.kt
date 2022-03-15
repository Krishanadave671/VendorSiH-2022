package com.example.vendo

import android.app.Application

class GlobalVariables : Application() {
    var languageCode: String? = "en"
    fun findLanguage(){
        languageCode = this.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
            .getString("languageCode","")
    }
}