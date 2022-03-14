package com.example.vendo;

import android.app.Application;

public class GlobalVariables extends Application {
    private String languageCode;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String someVariable) {
        this.languageCode = someVariable;
    }
}
