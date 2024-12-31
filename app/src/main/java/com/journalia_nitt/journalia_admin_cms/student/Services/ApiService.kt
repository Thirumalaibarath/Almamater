package com.example.journalia.Student.Services

import com.journalia_nitt.journalia_admin_cms.student.responses.BookMark
import com.journalia_nitt.journalia_admin_cms.student.responses.BookMarkFetch
import com.journalia_nitt.journalia_admin_cms.student.responses.DeadlineRequest
import com.journalia_nitt.journalia_admin_cms.student.responses.DeadlineResponse
import com.journalia_nitt.journalia_admin_cms.student.responses.LoginRequest
import com.journalia_nitt.journalia_admin_cms.student.responses.TokenResponse
import com.journalia_nitt.journalia_admin_cms.student.responses.ZResponse
import com.journalia_nitt.journalia_admin_cms.student.responses.userUploadResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentApiService {
    @POST("/access_token")
    fun getToken(@Body loginRequest: LoginRequest): Call<TokenResponse>
    @POST("/post_deadline")
    fun postDeadline(
        @Header("authorization") token: String,
        @Body deadlineRequest: DeadlineRequest
    ): Call<ZResponse>
}
interface handleBookMark{
    @POST("/bookmark/")
    suspend fun bookMark(@Body bookMark: BookMark): userUploadResponse

    @GET("/fetchBookmark/{token}")
    suspend fun getAllBook(@Path("token")token:String): BookMarkFetch
}
interface DeadlineApiService {
    @GET("get-deadlines")
    suspend fun getDeadlines(): Response<DeadlineResponse>
}



