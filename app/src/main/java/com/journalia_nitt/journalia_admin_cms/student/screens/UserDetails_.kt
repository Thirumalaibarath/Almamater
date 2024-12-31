package com.journalia_nitt.journalia_admin_cms.student.screens

import android.util.Base64
import org.json.JSONArray
import org.json.JSONObject

fun decodeJWT(jwt: String): Pair<String?, String?>? {
    try {
        val parts = jwt.split(".")
        if (parts.size != 3) throw IllegalArgumentException("Invalid JWT token")
        val payload = decodeBase64(parts[1])
        val jsonObject = JSONObject(payload)
        val name = jsonObject.optString("name", null)
        val email = jsonObject.optString("email", null)

        return Pair(name, email)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

private fun decodeBase64(encoded: String): String {
    return String(Base64.decode(encoded, Base64.URL_SAFE or Base64.NO_WRAP))
}

fun JSONObject.toMap(): Map<String, Any> {
    return keys().asSequence().associateWith { key ->
        when (val value = this[key]) {
            is JSONArray -> (0 until value.length()).map { value[it] }
            is JSONObject -> value.toMap()
            else -> value
        }
    }
}
