package com.journalia_nitt.journalia_admin_cms.firebase

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.getUserDetails
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


suspend fun fcmTokenToDataStore(context: Context) {
    val token = fetchFcmToken()
    val userDetails = getUserDetails(context)
    val db = FirebaseFirestore.getInstance()
    val documentRef = userDetails?.let { db.collection("students").document(it.collegeId) }
    val updates = mapOf("FCM_token" to token)
    documentRef?.update(updates)?.await()
}

private suspend fun fetchFcmToken(): String {
    return suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("FCM", "Fetching FCM registration token failed", task.exception)
                continuation.resume("") // Resume with an empty token or handle error as needed
                return@addOnCompleteListener
            }
            val token = task.result ?: ""
            Log.d("FCM", token)
            continuation.resume(token)
        }
    }
}
fun getLocalTime(): String {
    val calendar = Calendar.getInstance()
    val timeFormat = SimpleDateFormat("HH:mm:ss")
    return timeFormat.format(calendar.time)
}
suspend fun reminderToCalendar(title:String,date:String,context: Context):Result<String>
{
    val userDetails = getUserDetails(context = context)
    val db = FirebaseFirestore.getInstance()
    val documentRef = userDetails?.let { db.collection("calendar").document(it.collegeId) }
    return try {
        val updates = hashMapOf(title to date)
        documentRef?.update(updates as Map<String, Any>)?.await()
        Result.success("Document successfully updated!")
    } catch (e: Exception) {
        Result.failure(e)
    }
}
