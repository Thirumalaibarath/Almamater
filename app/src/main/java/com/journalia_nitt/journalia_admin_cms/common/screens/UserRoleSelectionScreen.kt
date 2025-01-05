package com.journalia_nitt.journalia_admin_cms.common.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.journalia_nitt.journalia_admin_cms.ui.theme.color_2
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun UserRoleSelectionScreen(navController: NavController)
{
    val buttonColor = Color(0XFFCDC1FF)
    Column(
        modifier = Modifier.fillMaxSize().background(color = color_2).padding(WindowInsets.systemBars.asPaddingValues()).background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp,Alignment.CenterVertically)
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "alma",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 50.sp,
                    color = Color(150,103,224)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text="mater",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 50.sp,
                    color = Color(188, 128, 240)
                )
            }
            Text(
                text="made for NIT Trichy",
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontSize = 15.sp,
                color = Color(150,103,224)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.raw.nitt_logo)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "SVG Logo",
                modifier = Modifier.size(100.dp)
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "Where do you belong?", fontFamily = urbanist, fontSize = 20.sp)
            Card(
                colors = CardDefaults.cardColors(containerColor = buttonColor),
                elevation = CardDefaults.cardElevation(15.dp),
                modifier = Modifier.shadow(0.dp, shape = RoundedCornerShape(12.dp)).fillMaxWidth(0.65f).padding(top = 20.dp)
                    .clickable {
                        navController.navigate(Screens.AdminLoginScreen.route)
                    }
            )
            {
                Text(text = "ADMIN BOARD" ,fontSize = 18.sp,modifier= Modifier.padding(0.dp,25.dp).align(Alignment.CenterHorizontally), fontFamily = urbanist,fontWeight = FontWeight.Bold)
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = buttonColor),
                elevation = CardDefaults.cardElevation(15.dp),
                modifier = Modifier.shadow(0.dp, shape = RoundedCornerShape(12.dp)).fillMaxWidth(0.85f).padding(top = 20.dp)
                    .clickable {
                        navController.navigate(Screens.StudentLoginScreen.route)
                    }
            )
            {
                Text(text = "STUDENT COMMUNITY",fontSize = 18.sp,modifier= Modifier.padding(0.dp,25.dp).align(Alignment.CenterHorizontally), fontFamily = urbanist,fontWeight = FontWeight.Bold)
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = buttonColor),
                elevation = CardDefaults.cardElevation(15.dp),
                modifier = Modifier.shadow(0.dp, shape = RoundedCornerShape(12.dp)).fillMaxWidth(0.65f).padding(top = 20.dp) .clickable {
                    navController.navigate(Screens.AlumniRegisterScreen.route)
                }
            )
            {
                Text(text = "ALUMNI CONNECT",fontSize = 18.sp,modifier= Modifier.padding(0.dp,25.dp).align(Alignment.CenterHorizontally), fontFamily = urbanist,fontWeight = FontWeight.Bold)
            }
        }
    }
}



