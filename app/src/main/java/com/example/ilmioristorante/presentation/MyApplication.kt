package com.example.ilmioristorante.presentation

import android.app.Application
//import com.example.ilmioristorante.data.security.initializeSecurityKeys
//import com.example.ilmioristorante.data.security.storeKeyInKeystore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}