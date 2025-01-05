package com.journalia_nitt.journalia_admin_cms.administration.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AdminHomeScreen(navController: NavController) {
    var mode by remember{ mutableIntStateOf(0)}
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
                        text = "Welcome to Student Welfare",
                        fontFamily = urbanist,
                        fontSize = 20.sp,
                        color = Color.Companion.Black,
                        fontWeight = FontWeight.Companion.Bold
                    )
                    Text(
                        text = "Dashboard",
                        fontFamily = urbanist,
                        fontSize = 20.sp,
                        color = Color.Companion.Black,
                        fontWeight = FontWeight.Companion.Bold
                    )
                }
                HomePageComponent(
                    event = {

                        navController.navigate(Screens.AdminDashboardScreen.route)
                    },
                    text = "ADMIN DASHBOARD"
                )
                HomePageComponent(
                    event = {
                        mode = 0
                        navController.navigate(Screens.AdminCreatePostScreen.route+"/$mode")
                    },
                    text = "POST A DEADLINE"
                )
                HomePageComponent(
                    event = {
                        mode = 1
                        navController.navigate(Screens.AdminCreatePostScreen.route+"/$mode")
                    },
                    text = "POST ANNOUNCEMENT"
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
                            text = "POST A QUERY",
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
                    colors = CardDefaults.cardColors(Color(163, 127, 219))
                ) {
                    Box( contentAlignment = Alignment.Center)
                    {
                        Text(
                            text = "CONTACT US",
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

@Composable
fun HomePageComponent(
    event:()->Unit,
    text:String
)
{
    Card(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .clickable {
                event()
            }
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(Color(205, 193, 255))
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Text(
                text = text,
                fontFamily = urbanist,
                fontSize = 17.sp,
                color = Color.Companion.Black,
                fontWeight = FontWeight.Companion.Bold,
            )
        }

    }
}