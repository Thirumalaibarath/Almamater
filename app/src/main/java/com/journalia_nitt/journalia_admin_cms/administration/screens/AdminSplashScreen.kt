package com.journalia_nitt.journalia_admin_cms.administration.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.sharedPreferences.getFromSharedPreferences
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AdminSplashScreen(innerPadding: PaddingValues, navController: NavController) {
    val context = LocalContext.current
    val token = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()).background(color = Color.White).clickable {
            try {
                token.value = getFromSharedPreferences(context , "token")
                if(token.value == "") {
//                    navController.navigate(Screens_in_Admin_cms.LoginPage.route)
                }
                else {
//                    navController.navigate(Screens_in_Admin_cms.LandingPage.createRoute(token.value))
                }
            }
            catch(e : Exception) {
                Log.d("message" , e.message.toString())
            }
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "alma",
                fontFamily = urbanist,
                fontSize = 50.sp,
                color = Color(0XFF9667E0),
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "mater",
                fontFamily = urbanist,
                fontSize = 50.sp,
                color = Color(0XFFBC80F0),
                fontWeight = FontWeight.ExtraBold
            )
        }
        Text(
            text = "made for NIT Trichy's Admin",
            color = Color(0XFF9667E0),
            fontFamily = urbanist,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.raw.nitt_logo)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "SVG Logo",
            modifier = Modifier.size(150.dp)
        )
    }
}