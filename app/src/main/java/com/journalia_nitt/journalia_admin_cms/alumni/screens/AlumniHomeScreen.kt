package com.journalia_nitt.journalia_admin_cms.alumni.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.journalia_nitt.journalia_admin_cms.administration.screens.HomePageComponent
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AlumniHomeScreen(
    navController: NavController
) {
    val alumni = remember { mutableStateOf(theUser.username) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.raw.nitt_logo)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "SVG Logo",
            modifier = Modifier.padding(top = 10.dp).size(100.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        )
        {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Companion.CenterHorizontally
            ) {
                Text(
//                    token.value
                    text = "Welcome to Alumni Connect",
                    fontFamily = urbanist,
                    fontSize = 20.sp,
                    color = Color.Companion.Black,
                )
                Text(
                    text = "mukesh",
                    fontFamily = urbanist,
                    fontSize = 20.sp,
                    color = Color.Companion.Black,
                    fontWeight = FontWeight.Companion.Bold
                )
            }
            HomePageComponent(
                event = {
                    navController.navigate(Screens.AlumniCommunityScreen.route)
                },
                text = "ALUMNI COMMUNITY"
            )
            HomePageComponent(
                event = {
                    navController.navigate(Screens.AlumniCreateAPostScreen.route)
                },
                text = "CREATE A POST"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(25.dp,0.dp,25.dp,10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(Color(163, 127, 219))
            ) {
                Box( contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Post A Query",
                        fontFamily = urbanist,
                        fontSize = 12.sp,
                        color = Color.Companion.White,
                        fontWeight = FontWeight.Companion.Bold,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(Color(0XFFA37FDB))
            ) {
                Box( contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Contact Us",
                        fontFamily = urbanist,
                        fontSize = 12.sp,
                        color = Color.Companion.White,
                        fontWeight = FontWeight.Companion.Bold,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }
    }
}