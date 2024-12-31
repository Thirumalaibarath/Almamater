package com.journalia_nitt.journalia_admin_cms.student.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.journalia_nitt.journalia_admin_cms.student.responses.userComments
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://dauth-sand.vercel.app/").addConverterFactory(GsonConverterFactory.create()).build()

val upvotesClient= retrofit.create(handleUpvotes::class.java)

interface handleUpvotes{
    @POST("/upvote/")
    suspend fun upvotesPost(@Query("token") token: String, @Query("file_id") file_id: String)

    @POST("/addComment/")
    suspend fun addComment(@Query("token") token: String, @Query("post_id") post_id: String,@Body comment: userComments)
}

class handleUserUpvotes:ViewModel(){

    fun upvotePost(token:String,file_id: String ){
        viewModelScope.launch {
            try {
                val response= upvotesClient.upvotesPost(token,file_id)
                Log.d("upvoteSuccessful","fuckYou")
            }
            catch (e:Exception){
                Log.d("errorPostUpload",e.message.toString())
            }
        }
    }

    fun addComment(token:String,post_id:String,comment: userComments){
        viewModelScope.launch {
            try {
                val response= upvotesClient.addComment(token,post_id,comment)
                Log.d("upvoteSuccessful","fuckYou")
            }
            catch (e:Exception){
                Log.d("errorPostUpload",e.message.toString())
            }
        }
    }

}
