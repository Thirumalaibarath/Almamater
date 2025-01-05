package com.journalia_nitt.journalia_admin_cms.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.getUserDetails
import kotlinx.coroutines.delay

@Composable
fun CommonSplashScreen(innerPadding: PaddingValues,navController: NavController){
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.raw.splash)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "SVG Logo",
            modifier = Modifier.size(300.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = "alma",
                color = Color(150, 103, 224),
                fontSize = 54.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(700)
            )
            Text(
                text = "mater",
                color=Color(188, 128, 240),
                fontSize = 54.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(700)
            )
        }
        Text(
            text = "connecting communities",
            color=Color(150, 103, 224),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.urbanist)),
            fontWeight = FontWeight(400)
        )
    }
    val localContext = LocalContext.current
    val nextPage = if(getUserDetails(context = localContext)?.loginStatus == true)
    {
        if(getUserDetails(context = localContext)?.role == "admin" )
        {
            Screens.AdminHomeScreen.route
        }
        else if(getUserDetails(context = localContext)?.role == "student")
        {
            Screens.StudentHomeScreen.route
        }
        else
        {
            Screens.AlumniHomeScreen.route
        }
    }
    else
    {
        Screens.UserRoleSelectionScreen.route
    }
    LaunchedEffect(Unit){
        delay(2000)
        navController.navigate(nextPage)
    }
}