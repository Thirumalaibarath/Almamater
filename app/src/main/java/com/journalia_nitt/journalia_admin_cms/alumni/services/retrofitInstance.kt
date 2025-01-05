package com.journalia_nitt.journalia_admin_cms.alumni.services

import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniAccount
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniUpload
import com.journalia_nitt.journalia_admin_cms.alumni.response.Comment
import com.journalia_nitt.journalia_admin_cms.alumni.response.CommentResponse
import com.journalia_nitt.journalia_admin_cms.alumni.response.FetchUploadsResponse
import com.journalia_nitt.journalia_admin_cms.alumni.response.FileUploadResponse
import com.journalia_nitt.journalia_admin_cms.alumni.response.GeneralResponse
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoginBody
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoginResponse
import com.journalia_nitt.journalia_admin_cms.alumni.response.UploadResponse
import com.journalia_nitt.journalia_admin_cms.alumni.response.Upvote
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://journaliaadmin.vercel.app/") // Emulator localhost access
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

val alumniAccountApi: AlumniAccountApi = retrofit.create(AlumniAccountApi::class.java)
val alumniUploadApi: AlumniUploadApi = retrofit.create(AlumniUploadApi::class.java)

interface AlumniAccountApi {
    @POST("/alumni/create-account/")
    suspend fun createAccount(@Body account: AlumniAccount): Response<GeneralResponse>

    @POST("/alumni/login/")
    suspend fun login(@Body request: LoginBody): Response<LoginResponse>
}

interface AlumniUploadApi {
    @POST("/alumni/upload/")
    suspend fun uploadContent(@Body upload: AlumniUpload): Response<UploadResponse>

    @GET("/alumni/uploads/")
    suspend fun fetchAllUploads(): Response<FetchUploadsResponse>

    @GET("/alumni/uploads/{username}")
    suspend fun fetchUploadsByUsername(@Path("username") username: String): Response<FetchUploadsResponse>

    @POST("/alumni/upload/{id}/comment/")
    suspend fun addComment(@Path("id") id: String, @Body comment: Comment): Response<CommentResponse>

    @POST("/alumni/upload/{id}/upvote/")
    suspend fun upvotePost(@Path("id") id: String, @Body upvote: Upvote): Response<GeneralResponse>

    @DELETE("/alumni/upload/{id}/delete/")
    suspend fun deletePost(@Path("id") id: String): Response<GeneralResponse>

    @POST("/alumni/upload/{id}/recreate/")
    suspend fun recreatePost(@Path("id") id: String, @Body upload: AlumniUpload): Response<UploadResponse>
}

// File upload API
interface FileUploadApi {
    @Multipart
    @POST("/upload/")
    suspend fun uploadFile(@Part file: MultipartBody.Part): Response<FileUploadResponse>
}

// Example of initializing the file upload API
val fileUploadApi: FileUploadApi = retrofit.create(FileUploadApi::class.java)

