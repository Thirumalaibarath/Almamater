package com.journalia_nitt.journalia_admin_cms.administration.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journalia_nitt.journalia_admin_cms.administration.FileUploadClient
import com.journalia_nitt.journalia_admin_cms.administration.response.forEachUser
import kotlinx.coroutines.launch

class AdminDetailsViewModel : ViewModel() {
    private val _detailsList = mutableStateListOf<forEachUser>()
    val detailsList: List<forEachUser> = _detailsList
    fun fetchAdminDetails() {
        viewModelScope.launch {
            try {
                val response = FileUploadClient.fetchAdminDetails()
                if (response.isSuccessful && response.body() != null) {
                    _detailsList.clear()
                    val fetchedData = response.body()!!
                    _detailsList.addAll(fetchedData.data)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("AdminDetails", "Fetch failed: ${response.code()}, Error: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("AdminDetails", "Error fetching details", e)
            }
        }
    }
}
