package com.journalia_nitt.journalia_admin_cms.api

import com.journalia_nitt.journalia_admin_cms.alumni.services.AlumniAccountApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    private const val BASE_URL = "https://alma-matar.vercel.app/"
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
// Create OkHttpClient with increased timeout
val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

// Initialize Retrofit instance
private val retrofit = Retrofit.Builder()
    .baseUrl("https://journaliaadmin.vercel.app/") // Emulator localhost access
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

// Create API clients
val alumniAccountApi: AlumniAccountApi = retrofit.create(AlumniAccountApi::class.java)
//val alumniUploadApi: AlumniUploadApi = retrofit.create(AlumniUploadApi::class.java)
