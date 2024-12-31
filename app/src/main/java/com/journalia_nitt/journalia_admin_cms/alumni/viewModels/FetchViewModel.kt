package com.journalia_nitt.journalia_admin_cms.alumni.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

//class AlumniUploadViewModel : ViewModel() {
//    val uploadStatus = mutableStateOf("")
//    val uploads = mutableStateListOf<AlumniUpload>()
//    val commentStatus = mutableStateOf("")
//    val upvoteStatus = mutableStateOf("")
//    fun fetchAllUploads() {
//        viewModelScope.launch {
//            try {
//                val response = alumniUploadApi.fetchAllUploads()
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    Log.d("AlumniUploadViewModel", "Raw Response: $responseBody")
//
//                    // Check if the response contains uploads and update the state
//                    if (responseBody != null && responseBody.uploads.isNotEmpty()) {
//                        uploads.clear()
//                        responseBody.uploads.forEach {
//                            uploads.addAll(it.uploads)  // Add the list of `AlumniUpload` to the state
//                        }
//                        Log.d("AlumniUploadViewModel", "Uploads successfully fetched: ${uploads.size}")
//                    } else {
//                        Log.d("AlumniUploadViewModel", "No uploads found.")
//                    }
//                } else {
//                    Log.e("AlumniUploadViewModel", "Error fetching uploads: ${response.code()}")
//                    uploadStatus.value = "Error fetching uploads: ${response.code()}"
//                }
//            } catch (e: Exception) {
//                Log.e("AlumniUploadViewModel", "Error fetching uploads", e)
//                uploadStatus.value = "Error: ${e.localizedMessage}"
//            }
//        }
//    }
//
//    fun addComment(comment: Comment) {
//        val id: String = comment.id ?: ""
//        viewModelScope.launch {
//            try {
//                val response = alumniUploadApi.addComment(id, comment)
//                if (response.isSuccessful) {
//                    commentStatus.value = response.body()?.message ?: "Comment added successfully."
//                } else {
//                    handleErrorResponse(response, commentStatus as MutableStateFlow<String>, "Failed to add comment.")
//                }
//            } catch (e: Exception) {
//                commentStatus.value = "Error: ${e.localizedMessage}"
//                Log.e("AlumniUploadViewModel", "Error adding comment", e)
//            }
//        }
//    }
//
//    fun upvotePost(uploadId: String, upvote: Upvote) {
//        viewModelScope.launch {
//            try {
//                val response = alumniUploadApi.upvotePost(uploadId, upvote)
//                if (response.isSuccessful) {
//                    upvoteStatus.value = response.body()?.message ?: "Upvoted successfully."
//                } else {
//                    handleErrorResponse(response, upvoteStatus as MutableStateFlow<String>, "Failed to upvote.")
//                }
//            } catch (e: Exception) {
//                upvoteStatus.value = "Error: ${e.localizedMessage}"
//                Log.e("AlumniUploadViewModel", "Error upvoting post", e)
//            }
//        }
//    }
//
//    private fun handleErrorResponse(
//        response: Response<*>,
//        status: MutableStateFlow<String>,
//        defaultErrorMessage: String
//    ) {
//        val errorBody = response.errorBody()?.string()
//        Log.e("AlumniAccountViewModel", "Error: ${response.code()}, Body: $errorBody")
//        status.update { errorBody ?: defaultErrorMessage }
//    }
//}