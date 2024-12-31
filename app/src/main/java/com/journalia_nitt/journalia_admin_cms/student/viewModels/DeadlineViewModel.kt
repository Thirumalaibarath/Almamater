package com.journalia_nitt.journalia_admin_cms.student.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journalia_nitt.journalia_admin_cms.student.deadlineApiService
import com.journalia_nitt.journalia_admin_cms.student.responses.Deadline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DeadlineViewModel : ViewModel() {
    private val _deadlines = MutableStateFlow<List<Deadline>>(emptyList())
    val deadlines: StateFlow<List<Deadline>> get() = _deadlines

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchDeadlines() {
        viewModelScope.launch {
            try {
                val response = deadlineApiService.getDeadlines() // Call API directly here
                if (response.isSuccessful) {
                    response.body()?.let { deadlineResponse ->
                        // Flatten the deadlines list
                        val allDeadlines = deadlineResponse.deadlines.flatMap { it.details }
                        _deadlines.value = allDeadlines
                        Log.d("DeadlineViewModel", "Fetched deadlines: $allDeadlines")
                    }
                } else {
                    _error.value = "Error: ${response.code()}"
                    Log.d("DeadlineViewModel", "Error: ${error.value.toString()}")
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.d("DeadlineViewModel", "Error: ${e.toString()}")
            }
        }
    }
}


