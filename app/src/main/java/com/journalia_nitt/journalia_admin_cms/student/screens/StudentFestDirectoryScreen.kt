package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun StudentFestDirectoryScreen(navController: NavController) {

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(),
    ){
        Box(
            modifier= Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text="FESTIVALS & EVENTS",
                fontFamily= urbanist,
                fontSize=24.sp,
                fontWeight= FontWeight(600),
            )

        }
        Box(modifier= Modifier
            .fillMaxWidth()
            .height(380.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier= Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(
                            width = 180.dp,
                            height = 180.dp
                        )
                            .shadow(4.dp, shape = RoundedCornerShape(20.dp)),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(163, 127, 219))
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.festember),
                                contentDescription = "Festember",
                                modifier = Modifier.size(
                                    width = 80.dp,height=90.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x=-10.dp,y=-10.dp)
                            ){
                                Button(
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(50.dp)
                                ){}
                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(30.dp),
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "User",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier.size(
                            width = 180.dp,
                            height = 180.dp
                        )
                            .shadow(4.dp, shape = RoundedCornerShape(20.dp)),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(163, 127, 219))
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pragyan),
                                contentDescription = "Pragyan",
                                modifier = Modifier
                                    .size(
                                        width = 180.dp, height = 90.dp
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x=-10.dp,y=-10.dp)
                            ){
                                Button(
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(50.dp)
                                ){}
                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(30.dp),
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "User",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(
                            width = 180.dp,
                            height = 180.dp
                        )
                            .shadow(4.dp, shape = RoundedCornerShape(20.dp)),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(163, 127, 219))
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.nittfest),
                                contentDescription = "Nittfest",
                                modifier = Modifier.size(
                                    width = 80.dp,height=90.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x=-10.dp,y=-10.dp)
                            ){
                                Button(
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(50.dp)
                                ){}
                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(30.dp),
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "User",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier.size(
                            width = 180.dp,
                            height = 180.dp
                        )
                            .shadow(4.dp, shape = RoundedCornerShape(20.dp)),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(163, 127, 219))
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.aaveg),
                                contentDescription = "Aaveg",
                                modifier = Modifier.size(
                                    width = 80.dp,height=90.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x=-10.dp,y=-10.dp)
                            ){
                                Button(
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(50.dp)
                                ){}
                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(30.dp),
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "User",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }

    }

}