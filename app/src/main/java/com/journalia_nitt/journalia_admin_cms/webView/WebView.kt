package com.journalia_nitt.journalia_admin_cms.webView

import android.webkit.WebSettings
import androidx.compose.runtime.Composable
import android.webkit.WebView
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

@Composable
fun WebView(url:String) {
    AndroidView(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()).background(color = Color.Black),
        factory = { context ->
            WebView(context).apply {
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl(url)
            }
        }
    )
}

