package com.journalia_nitt.journalia_admin_cms.common.application

import android.app.Application;
import com.google.firebase.FirebaseApp

class Almamater : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
