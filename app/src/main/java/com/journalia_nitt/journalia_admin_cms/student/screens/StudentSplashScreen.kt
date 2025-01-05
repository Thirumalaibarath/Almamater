package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R

@Composable
fun StudentSplashScreen(innerPadding: PaddingValues, navController: NavController){
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ){
        Spacer(modifier = Modifier.padding(70.dp))
        Box(
            Modifier
            .fillMaxWidth()
            .height(350.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(R.drawable.splash),
                contentDescription = "splash",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Box(modifier = Modifier.fillMaxWidth().height(70.dp),
            contentAlignment = Alignment.Center){
            Row(modifier = Modifier.width(350.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "alma",
                    color = Color(150, 103, 224),
                    fontSize = 54.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(700)
                )
                Text(
                    text = "mater",
                    color= Color(188, 128, 240),
                    fontSize = 54.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(700)
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(70.dp),
            contentAlignment = Alignment.Center){
            Text(
                text = "connecting communities",
                color= Color(150, 103, 224),
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(400)
            )
        }
    }

}