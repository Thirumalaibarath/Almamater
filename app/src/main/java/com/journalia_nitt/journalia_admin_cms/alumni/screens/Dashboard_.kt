package com.journalia_nitt.journalia_admin_cms.alumni.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.landingPageButtonTexts
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_alumni_cms
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun LandingPage(innerPadding: PaddingValues ,
                navController: NavController
) {
    val alumni = remember { mutableStateOf(theUser.username)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(Color.Companion.White)
        ) {
            Box() {
                Column(
                    modifier = Modifier
                ) {
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "alma mater",
                            fontFamily = urbanist,
                            fontSize = 28.sp,
                            color = Color.Companion.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        modifier = Modifier.padding(end = 10.dp, top = 10.dp),
                        onClick = {
                            navController.navigate(Screens_in_alumni_cms.loginPage.route)
                        }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.signout),
                            contentDescription = "Sign out button",
                            modifier = Modifier.scale(3f)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 120.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.nitt),
                    contentDescription = "Logo",
                    modifier = Modifier.scale(3.5f)
                )
            }
            Spacer(modifier = Modifier.padding(top = 60.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Companion.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to Alumni Connect ",
                    fontFamily = urbanist,
                    fontSize = 20.sp,
                    color = Color.Companion.Black,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = alumni.value,
                    fontFamily = urbanist,
                    fontSize = 24.sp,
                    color = Color.Companion.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
            for (i in landingPageButtonTexts) {
                Spacer(modifier = Modifier.padding(top = 25.dp))
                Card(
                    modifier = Modifier
                        .height(77.dp)
                        .width(241.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            navController.navigate(i.second.route)
                        }
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color.Companion.Black,
                            ambientColor = Color.Companion.Black,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(Color(205, 193, 255))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.Companion.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = i.first,
                            fontFamily = urbanist,
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Companion.Bold
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Companion.Bottom
            ) {
                Card(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.5f)
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color.Companion.Black,
                            ambientColor = Color.Companion.Black,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(Color(163, 127, 219))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.Companion.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Post a Query",
                            fontFamily = urbanist,
                            fontSize =16.sp,
                            color = Color.Companion.White,
                            fontWeight = FontWeight.Companion.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Card(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.5f)
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color.Companion.Black,
                            ambientColor = Color.Companion.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            navController.navigate(Screens_in_alumni_cms.contactUs.route)
                        },
                    colors = CardDefaults.cardColors(Color(163, 127, 219))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.Companion.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Contact us",
                            fontFamily = urbanist ,
                            fontSize = 16.sp,
                            color = Color.Companion.White,
                            fontWeight = FontWeight.Companion.Bold
                        )
                    }
                }
            }
        }
    }
}