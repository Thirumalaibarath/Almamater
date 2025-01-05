package com.journalia_nitt.journalia_admin_cms.administration

import androidx.compose.runtime.mutableStateOf
import com.journalia_nitt.journalia_admin_cms.administration.response.Deadline
import com.journalia_nitt.journalia_admin_cms.administration.services.FileUpload
import com.journalia_nitt.journalia_admin_cms.administration.services.Login
import com.journalia_nitt.journalia_admin_cms.administration.services.Secret
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit= Retrofit.Builder()
    .baseUrl("https://journaliaadmin.vercel.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val FileUploadClient= retrofit.create(FileUpload::class.java)
val LoginClient = retrofit.create(Login::class.java)
val SecretClient = retrofit.create(Secret::class.java)
val infoPasser = mutableStateOf(Deadline("", "", "", "", "", "", 0, ""))

