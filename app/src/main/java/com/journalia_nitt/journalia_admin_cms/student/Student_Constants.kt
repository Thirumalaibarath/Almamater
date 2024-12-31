package com.journalia_nitt.journalia_admin_cms.student

import android.content.ContentResolver
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import com.example.journalia.Student.Services.DeadlineApiService
import com.example.journalia.Student.Services.handleBookMark
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val retrofit = Retrofit.Builder().baseUrl("https://dauth-sand.vercel.app/").addConverterFactory(
    GsonConverterFactory.create()).build()
val bookMarkHandle= retrofit.create(handleBookMark::class.java)
val days = arrayOf(
    "Su",
    "Mo",
    "Tu",
    "We",
    "Th",
    "Fr",
    "Sa"
)
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(30, TimeUnit.SECONDS) // Increase connection timeout
    .readTimeout(30, TimeUnit.SECONDS)    // Increase read timeout
    .writeTimeout(30, TimeUnit.SECONDS)   // Increase write timeout
    .build()

val RetrofitInstance = Retrofit.Builder()
    .baseUrl("https://brucewaynegotham007.pythonanywhere.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()
var Uri1 = mutableStateOf<Uri?>(null)
var ContentResolver1 = mutableStateOf<ContentResolver?>(null)
val deadlineApiService = RetrofitInstance.create(DeadlineApiService::class.java)

var pdfUrlGlobal = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"
