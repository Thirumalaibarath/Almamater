package com.journalia_nitt.journalia_admin_cms.administration

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.journalia_nitt.journalia_admin_cms.administration.response.Deadline
import com.journalia_nitt.journalia_admin_cms.administration.services.FileUpload
import com.journalia_nitt.journalia_admin_cms.administration.services.Login
import com.journalia_nitt.journalia_admin_cms.administration.services.Secret
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_Admin_cms
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)  // Increase timeout duration
    .readTimeout(30, TimeUnit.SECONDS)
    .build()
private val retrofit= Retrofit.Builder()
    .baseUrl("https://journaliaadmin.vercel.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val FileUploadClient= retrofit.create(FileUpload::class.java)
val LoginClient = retrofit.create(Login::class.java)
val SecretClient = retrofit.create(Secret::class.java)
val infoPasser = mutableStateOf(Deadline("", "", "", "", "", "", 0, ""))
val landingPageButtonTexts = listOf(
    Pair("ADMIN DASHBOARD", Screens_in_Admin_cms.AdminPage),
    Pair("POST A DEADLINE" , Screens_in_Admin_cms.DeadlinePage),
    Pair("POST ANNOUNCEMENT" , Screens_in_Admin_cms.AnnouncementPage)
)
val mode = mutableIntStateOf(0)