package com.journalia_nitt.journalia_admin_cms.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.core.app.NotificationCompat
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.work.*
import java.util.concurrent.TimeUnit
import androidx.core.app.ActivityCompat
import android.Manifest
import android.app.Activity
import java.util.Calendar

const val REQUEST_CODE_NOTIFICATION_PERMISSION = 1001

class Almamater : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .build()
//        WorkManager.initialize(this, config)
    }
}

// Worker to handle the background notification logic
class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("NotificationWorker", "Executing WorkManager task...")
        try {
            sendNotification("Alarm Triggered!", "This is your scheduled notification.")
        } catch (e: Exception) {
            Log.e("NotificationWorker", "Failed to send notification: ${e.message}")
            return Result.failure()
        }
        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val channelId = "ALARM_CHANNEL"
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for devices running Android 8.0 (API 26) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Alarm Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Replace with your app's icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true) // Dismiss notification on click
            .build()

        notificationManager.notify(1001, notification) // Ensure unique ID for each notification
        Log.d("NotificationWorker", "Notification sent successfully!")
    }
}

// Function to schedule a notification at a specific time
fun scheduleNotificationAtSpecificTime(context: Context, targetHour: Int, targetMinute: Int) {
    // Get current time
    val now = Calendar.getInstance()

    // Set target time (e.g., 3:00 PM)
    val targetTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, targetHour)
        set(Calendar.MINUTE, targetMinute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    // If the target time is before the current time, set it for tomorrow
    if (targetTime.before(now)) {
        targetTime.add(Calendar.DAY_OF_MONTH, 1)
    }

    // Calculate the delay in milliseconds
    val delayInMillis = targetTime.timeInMillis - now.timeInMillis

    // Schedule the notification using WorkManager
    val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(delayInMillis, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)

    Log.d("NotificationWorker", "Notification scheduled at ${targetTime.time}")
}

// Jetpack Compose UI
@Composable
fun AlarmNotificationUI(activity : Activity , context: Context, innerPadding: PaddingValues) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            REQUEST_CODE_NOTIFICATION_PERMISSION
        )
    }
    Button(
        onClick = {
            scheduleNotificationAtSpecificTime(context,12,58)
        },
        modifier = Modifier.padding(innerPadding)
    ) {
        Text(text = "Schedule Notification")
    }
}
