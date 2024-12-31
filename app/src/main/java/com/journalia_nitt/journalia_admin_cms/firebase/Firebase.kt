package com.journalia_nitt.journalia_admin_cms.firebase

import android.content.Context
import android.util.Log
import com.example.journalia.Student.SharedPreferences.getUserDetails
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun fireStore(context :Context): List<Pair<String, String>> {
    val events = mutableListOf<Pair<String, String>>()
    val db = FirebaseFirestore.getInstance()
    val usersCollection = db.collection("calendar")
    val userDetails = getUserDetails(context = context)
    return try {
        val documents = usersCollection.get().await()
        for (document in documents) {
            if (userDetails != null) {
                if (document.id == userDetails.collegeId ) {
                    for ((event, date) in document.data) {
                        events.add(Pair(date.toString(), event.toString()))
                    }
                }
            }
        }
        events
    } catch (e: Exception) {
        Log.w("Firestore", "Error getting documents: ", e)
        emptyList()
    }
}
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
        documentRef?.set(updates as Map<String, Any>)?.await()
        Result.success("Document successfully updated!")
    } catch (e: Exception) {
        Result.failure(e)
    }
}
