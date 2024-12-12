package com.journalia_nitt.journalia_admin_cms

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import androidx.compose.runtime.State
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class FileUploadViewModel : ViewModel() {
    val uploadStatus = mutableStateOf("")
    private val _isLoaded = mutableStateOf(false)
    val isLoaded: State<Boolean>  = _isLoaded
    val fileUrl = mutableStateOf<String?>("")
    fun uploadFile(fileUri: Uri?, contentResolver: ContentResolver?) {
        viewModelScope.launch {
            try {
                if (fileUri == null || contentResolver == null) {
                    Log.e("FileUpload", "Invalid URI or ContentResolver")
                    uploadStatus.value = "success1"
                    return@launch
                }

                // Read file into memory
                val byteArray = contentResolver.openInputStream(fileUri)?.use { it.readBytes() }
                    ?: throw Exception("Unable to read file contents")

                val mimeType = contentResolver.getType(fileUri) ?: "application/octet-stream"
                val requestBody = RequestBody.create(mimeType.toMediaTypeOrNull(), byteArray)

                val multipartBody = MultipartBody.Part.createFormData(
                    "file",
                    getFileName(contentResolver, fileUri),
                    requestBody
                )

                val response = FileUploadClient.uploadFile(multipartBody)
                if (response.isSuccessful && response.body() != null) {
                    fileUrl.value = response.body()?.url
                    Log.d("FileUpload", "Upload successful: ${response.body()?.url}")
                    uploadStatus.value = "success"
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("FileUpload", "Upload failed: ${response.code()}, Error: $errorBody")
                    uploadStatus.value = "failure"
                }

                _isLoaded.value = true
            } catch (e: Exception) {
                uploadStatus.value = "failure"
                Log.e("FileUpload", "Error uploading file", e)
            }
        }
    }


    fun uploadDetailsDeadline(details: AdminDashBoardInfo){
        viewModelScope.launch{
            try{
                val response=FileUploadClient.detailsUpload(details)
                Log.d("FileUploadDetails", "Upload successful: $response")
            }
            catch (e:Exception){
                Log.e("FileUploadDetails", "Error uploading file", e)
            }
        }
    }
}