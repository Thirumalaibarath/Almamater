package com.example.journalia.WebView

import androidx.compose.runtime.Composable
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.firebase.fcmTokenToDataStore
import com.journalia_nitt.journalia_admin_cms.student.screens.decodeJWT
import com.example.journalia.Student.SharedPreferences.saveUserDetails
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun buildUrl(): String {
    val baseURL = "https://auth.delta.nitt.edu/authorize"
    val params = mapOf(
        "client_id" to "B-N8ma.~1IAIrS5L",
        "redirect_uri" to "https://alma-matar.vercel.app/redirect",
        "response_type" to "code",
        "grant_type" to "authorization_code",
        "state" to "code",
        "scope" to "email+openid+profile+user",
        "nonce" to ""
    )
    val queryString = params.map { (key, value) ->
        "${key}=${value}"
    }.joinToString("&")
    return "$baseURL?$queryString"
}

@Composable
fun WebView(navController: NavController,url:String) {
    val localContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    AndroidView(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()).background(color = Color.Black),
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        url?.let {
                            if (it.contains("id_token=")) {
                                val idToken = extractIdToken(it)
                                decodeJWT(idToken.toString())?.first?.let { it_1 ->
                                    decodeJWT(idToken.toString())?.second?.let { it_2 ->
                                        saveUserDetails(localContext,
                                            it_1, it_2
                                        )
                                    }
                                }
                                coroutineScope.launch(Dispatchers.IO) {
                                    fcmTokenToDataStore(context = context)
                                }
                                navController.navigate(Screens.HomePage.route){
                                    popUpTo(Screens.LoginPage.route) { inclusive = true }
                                }
                            }
                        }
                    }
                }
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl(url)
            }
        }
    )
}
fun extractIdToken(url: String): String? {
    val query = url.substringAfter("?", "")
    val params = query.split("&").associate {
        val (key, value) = it.split("=")
        key to value
    }
    return params["id_token"]
}
