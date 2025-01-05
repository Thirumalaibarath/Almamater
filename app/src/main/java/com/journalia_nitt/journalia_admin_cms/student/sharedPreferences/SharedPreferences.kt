package com.journalia_nitt.journalia_admin_cms.student.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.journalia_nitt.journalia_admin_cms.student.responses.UserInfo

fun saveUserDetails(context: Context, name: String, email: String,role:String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("role",role)
    editor.putString("name", name)
    editor.putString("email", email)
    editor.putBoolean("login_status",true)
    editor.apply()
}
fun saveTokenDetails(context: Context, token: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("token",token)
    editor.apply()
}
fun getTokenDetails(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("token", null)
    return token
}
fun getUserDetails(context: Context): UserInfo? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val name = sharedPreferences.getString("name", null)
    val email = sharedPreferences.getString("email", null)
    val role = sharedPreferences.getString("role", null)
    val loginStatus = sharedPreferences.getBoolean("login_status",false)
    return if (name != null && email != null && role != null) {
        UserInfo(name, email,loginStatus,role)
    } else {
        null
    }
}
fun clearUserDetails(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.putBoolean("login_status", false)
    editor.apply()
}