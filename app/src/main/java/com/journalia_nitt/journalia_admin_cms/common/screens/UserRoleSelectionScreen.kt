package com.journalia_nitt.journalia_admin_cms.common.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens

@Composable
fun LoginPage(navController: NavController)
{
    val buttonColor = Color(0XFFCDC1FF)
    Column(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
            fontSize = 20.sp,
            color = Color(150,103,224)
        )
        Image(
            painter = painterResource(id = R.drawable.nitt_logo),
            contentDescription = "logo",
            modifier = Modifier.size(200.dp).padding(0.dp,20.dp),
            contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Where do you belong?", fontStyle = FontStyle.Italic, fontSize = 20.sp)
        Card(
            colors = CardDefaults.cardColors(containerColor = buttonColor),
            modifier = Modifier.shadow(0.dp, shape = RoundedCornerShape(12.dp)).fillMaxWidth(0.65f).padding(top = 20.dp)
        )
        {
            Text(text = "ADMIN BOARD",modifier= Modifier.padding(0.dp,15.dp).align(Alignment.CenterHorizontally),fontWeight = FontWeight.Bold)
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = buttonColor),
            modifier = Modifier.shadow(0.dp, shape = RoundedCornerShape(12.dp)).fillMaxWidth(0.85f).padding(top = 20.dp)
                .clickable {
                    navController.navigate(Screens.AuthPage.route)
                }
        )
        {
            Text(text = "STUDENT COMMUNITY",modifier= Modifier.padding(0.dp,15.dp).align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold)
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = buttonColor),
            modifier = Modifier.shadow(0.dp, shape = RoundedCornerShape(12.dp)).fillMaxWidth(0.65f).padding(top = 20.dp)
        )
        {
            Text(text = "ALUMNI CONNECT",modifier= Modifier.padding(0.dp,15.dp).align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold)
        }


    }
}



