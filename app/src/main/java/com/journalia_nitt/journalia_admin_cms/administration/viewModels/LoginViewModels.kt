package com.journalia_nitt.journalia_admin_cms.administration.viewModels

import androidx.lifecycle.ViewModel
import com.journalia_nitt.journalia_admin_cms.administration.LoginClient
import com.journalia_nitt.journalia_admin_cms.administration.SecretClient
import com.journalia_nitt.journalia_admin_cms.administration.response.LoginBody
import com.journalia_nitt.journalia_admin_cms.administration.response.SecretBody

class LoginViewModel : ViewModel() {
    suspend fun login(email: String, password: String): Boolean {
        val response = LoginClient.login(
            LoginBody(email = email, password = password)
        )
        return response.isSuccessful
    }
}

class SecretViewModel : ViewModel() {
    suspend fun checkSecret(email: String, secret: String): String? {
        val response = SecretClient.secret(
            SecretBody(
                email = email,
                secret = secret
            )
        )
        return response.token
    }
}
