package com.journalia_nitt.journalia_admin_cms.administration.response

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class SecretBody(
    @SerializedName("email") val email: String,
    @SerializedName("secret") val secret: String
)

data class LoginResponse(
    val message: String,
    val isSuccessful : Boolean
)

data class SecretResponse(
    val token: String?,
    val message: String
)
data class FileData(
    val name: String,
    val mimeType: String,
    val content: ByteArray
)