package com.journalia_nitt.journalia_admin_cms.student.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import android.content.ActivityNotFoundException
import android.util.Log
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebView
import androidx.navigation.NavController
import android.content.pm.PackageManager
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.pdfUrlGlobal

fun openPdf(
    context: Context,
    pdfUrl: String,
    navController: NavController
) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(Uri.parse(pdfUrlGlobal), "application/pdf")
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val packageManager = context.packageManager
        val resolvedInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        if (resolvedInfo.isNotEmpty()) {
            context.startActivity(intent)
        }
        else {
            Toast.makeText(context, "No PDF reader found. Please install one.", Toast.LENGTH_LONG).show()
//            val encodedPdfUrl = try {
//                URLEncoder.encode(pdfUrl, "UTF-8")
//            } catch (e: UnsupportedEncodingException) {
//                e.printStackTrace()
//                pdfUrl
//            }
//            pdfUrlGlobal = "https://docs.google.com/gview?embedded=true&url=$encodedPdfUrl"
            navController.navigate(Screens.PdfWebViewPage.route)
        }
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "Error opening PDF. Please try again later.", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun PDFWebViewScreen() {
    Log.d("pdfurl", pdfUrlGlobal)
    AndroidView(
        factory = { context ->
            val webView = WebView(context)
            webView.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(pdfUrlGlobal)
            }
            webView
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ShowImageInDialog(
    showDialog : MutableState<Boolean>,
    url : String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Button(onClick = { showDialog.value = true }) {
            Text(text = "Show Image")
        }

        if (showDialog.value) {
            AlertDialog(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                onDismissRequest = { showDialog.value = false },
                title = {
//                    Text(text = "Image Preview")
                },
                text = {
                    Image(
                        painter = rememberImagePainter(
                            data = url,
                            builder = {
                                crossfade(true)
                            }
                        ),
                        contentDescription = "Cloudinary Image",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                },
                confirmButton = {
//                    Button(
//                        onClick = { showDialog.value = false },
//                        colors = ButtonDefaults.buttonColors(Color.Blue)
//                    ) {
//                        Text(text = "Close", color = Color.White)
//                    }
                }
            )
        }
    }
}

