package com.example.journalia.Common.Application

import android.app.Application;
import com.google.firebase.FirebaseApp

class Almamater : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
