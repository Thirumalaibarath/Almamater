package com.journalia_nitt.journalia_admin_cms.alumni.viewModels

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniAccount
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniUpload
import com.journalia_nitt.journalia_admin_cms.alumni.response.Comment
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoggedInAccount
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoginBody
import com.journalia_nitt.journalia_admin_cms.alumni.response.Upvote
import com.journalia_nitt.journalia_admin_cms.alumni.services.alumniAccountApi
import com.journalia_nitt.journalia_admin_cms.alumni.services.alumniUploadApi
import com.journalia_nitt.journalia_admin_cms.alumni.services.fileUploadApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class AlumniAccountViewModel : ViewModel() {
    private val _createStatus = MutableStateFlow("")
    val createStatus: StateFlow<String> = _createStatus

    private val _loginStatus = MutableStateFlow("")
    val loginStatus: StateFlow<String> = _loginStatus

    private val _loggedInAccount = MutableStateFlow<LoggedInAccount?>(null)
    val loggedInAccount: StateFlow<LoggedInAccount?> = _loggedInAccount

    fun createAccount(account: AlumniAccount) {
        viewModelScope.launch {
            try {
                val response = alumniAccountApi.createAccount(account)
                if (response.isSuccessful) {
                    _createStatus.update { response.body()?.message ?: "Account created successfully." }
                } else {
                    handleErrorResponse(response, _createStatus, "Account creation failed.")
                }
            } catch (e: Exception) {
                _createStatus.update { "Error: ${e.localizedMessage}" }
                Log.e("AlumniAccountViewModel", "Error creating account", e)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = alumniAccountApi.login(LoginBody(email, password))
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _loginStatus.update { responseBody?.message ?: "Login successful." }
                    _loggedInAccount.update { responseBody?.logged_account }
                } else {
                    handleErrorResponse(response, _loginStatus, "Invalid login credentials.")
                }
            } catch (e: Exception) {
                _loginStatus.update { "Error: ${e.localizedMessage}" }
                Log.e("AlumniAccountViewModel", "Error logging in", e)
            }
        }
    }

    private fun handleErrorResponse(
        response: Response<*>,
        status: MutableStateFlow<String>,
        defaultErrorMessage: String
    ) {
        val errorBody = response.errorBody()?.string()
        Log.e("AlumniAccountViewModel", "Error: ${response.code()}, Body: $errorBody")
        status.update { errorBody ?: defaultErrorMessage }
    }
}



class AlumniUploadViewModel : ViewModel() {
    val uploadStatus = mutableStateOf("")
    val uploads = mutableStateListOf<AlumniUpload>()
    val commentStatus = mutableStateOf("")
    val upvoteStatus = mutableStateOf("")

    fun uploadContent(upload: AlumniUpload) {
        viewModelScope.launch {
            try {
                val response = alumniUploadApi.uploadContent(upload)
                if (response.isSuccessful) {
                    uploadStatus.value = response.body()?.message ?: "Upload successful."
                } else {
                    handleErrorResponse(response, uploadStatus, "Upload failed.")
                }
            } catch (e: Exception) {
                uploadStatus.value = "Error: ${e.localizedMessage}"
                Log.e("AlumniUploadViewModel", "Error uploading content", e)
            }
        }
    }

    fun fetchAllUploads() {
        viewModelScope.launch {
            try {
                val response = alumniUploadApi.fetchAllUploads()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("AlumniUploadViewModel", "Raw Response: $responseBody")

                    // Check if the response contains uploads and update the state
                    if (responseBody != null && responseBody.uploads.isNotEmpty()) {
                        // Clear existing uploads and add new ones from the response
                        uploads.clear()

                        // Iterate through the response and extract the `uploads` from each `UploadOutput`
                        responseBody.uploads.forEach {
                            uploads.addAll(it.uploads)  // Add the list of `AlumniUpload` to the state
                        }
                        Log.d("AlumniUploadViewModel", "Uploads successfully fetched: ${uploads.size}")
                    } else {
                        Log.d("AlumniUploadViewModel", "No uploads found.")
                    }
                } else {
                    Log.e("AlumniUploadViewModel", "Error fetching uploads: ${response.code()}")
                    uploadStatus.value = "Error fetching uploads: ${response.code()}"
                }
            } catch (e: Exception) {
                Log.e("AlumniUploadViewModel", "Error fetching uploads", e)
                uploadStatus.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun addComment(comment: Comment) {
        val id: String = comment.id ?: ""
        viewModelScope.launch {
            try {
                val response = alumniUploadApi.addComment(id, comment)
                if (response.isSuccessful) {
                    commentStatus.value = response.body()?.message ?: "Comment added successfully."
                } else {
                    handleErrorResponse(response, commentStatus, "Failed to add comment.")
                }
            } catch (e: Exception) {
                commentStatus.value = "Error: ${e.localizedMessage}"
                Log.e("AlumniUploadViewModel", "Error adding comment", e)
            }
        }
    }

    fun upvotePost(uploadId: String, upvote: Upvote) {
        viewModelScope.launch {
            try {
                val response = alumniUploadApi.upvotePost(uploadId, upvote)
                if (response.isSuccessful) {
                    upvoteStatus.value = response.body()?.message ?: "Upvoted successfully."
                } else {
                    handleErrorResponse(response, upvoteStatus, "Failed to upvote.")
                }
            } catch (e: Exception) {
                upvoteStatus.value = "Error: ${e.localizedMessage}"
                Log.e("AlumniUploadViewModel", "Error upvoting post", e)
            }
        }
    }

    fun deletePost(uploadId: String) {
        viewModelScope.launch {
            try {
                val response = alumniUploadApi.deletePost(uploadId)
                if (response.isSuccessful) {
                    uploadStatus.value = response.body()?.message ?: "Post deleted successfully."
                    // Optionally, remove the deleted post from the local list
                    uploads.removeIf { it.id == uploadId }
                } else {
                    handleErrorResponse(response, uploadStatus, "Failed to delete post.")
                }
            } catch (e: Exception) {
                uploadStatus.value = "Error: ${e.localizedMessage}"
                Log.e("AlumniUploadViewModel", "Error deleting post", e)
            }
        }
    }

    fun recreatePost(uploadId: String, upload: AlumniUpload) {
        viewModelScope.launch {
            try {
                val response = alumniUploadApi.recreatePost(uploadId, upload)
                if (response.isSuccessful) {
                    val updatedUpload = response.body()?.data
                    if (updatedUpload != null) {
                        // Update the post in the local list
                        uploads.replaceAll { if (it.id == uploadId) updatedUpload else it }
                        uploadStatus.value = "Post recreated successfully."
                    } else {
                        uploadStatus.value = "Failed to update the post locally."
                    }
                } else {
                    handleErrorResponse(response, uploadStatus, "Failed to recreate post.")
                }
            } catch (e: Exception) {
                uploadStatus.value = "Error: ${e.localizedMessage}"
                Log.e("AlumniUploadViewModel", "Error recreating post", e)
            }
        }
    }


    private fun handleErrorResponse(
        response: Response<*>,
        status: MutableState<String>,
        defaultErrorMessage: String
    ) {
        val errorBody = response.errorBody()?.string()
        Log.e("AlumniUploadViewModel", "Error: ${response.code()}, Body: $errorBody")
        status.value = errorBody ?: defaultErrorMessage
    }
}


class FileUploadViewModel : ViewModel() {

    private val _uploadFileStatus = MutableStateFlow("")
    val uploadFileStatus: StateFlow<String> get() = _uploadFileStatus

    private val _uploadedFileUrl = MutableStateFlow("")
    val uploadedFileUrl: StateFlow<String> get() = _uploadedFileUrl

    fun uploadFile(fileUri: Uri, contentResolver: ContentResolver) {
        viewModelScope.launch {
            try {
                val inputStream = contentResolver.openInputStream(fileUri)
                if (inputStream == null) {
                    _uploadFileStatus.value = "Error: Unable to read file contents."
                    return@launch
                }
                val byteArray = inputStream.readBytes()
                val requestBody = byteArray.toRequestBody("application/octet-stream".toMediaTypeOrNull())
                val filePart = MultipartBody.Part.createFormData(
                    "file", fileUri.lastPathSegment ?: "file", requestBody
                )

                val response = fileUploadApi.uploadFile(filePart)
                if (response.isSuccessful) {
                    _uploadFileStatus.value = response.body()?.message ?: "File uploaded successfully."
                    _uploadedFileUrl.value = response.body()?.url.orEmpty()
                } else {
                    handleErrorResponse(response, _uploadFileStatus, "File upload failed.")
                }
            } catch (e: Exception) {
                _uploadFileStatus.value = "Error: ${e.localizedMessage}"
                Log.e("FileUploadViewModel", "Error uploading file", e)
            }
        }
    }

    private fun handleErrorResponse(
        response: Response<*>,
        status: MutableStateFlow<String>,
        defaultErrorMessage: String
    ) {
        val errorBody = response.errorBody()?.string()
        Log.e("FileUploadViewModel", "Error: ${response.code()}, Body: $errorBody")
        status.value = errorBody ?: defaultErrorMessage
    }
}

