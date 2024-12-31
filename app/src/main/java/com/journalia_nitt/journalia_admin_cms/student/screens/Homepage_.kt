package com.journalia_nitt.journalia_admin_cms.student.screens

import android.graphics.Color.rgb
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.journalia.Student.SharedPreferences.getUserDetails
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.BottomBar
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.navigation.SideBar
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist


@Composable
fun LandingPage(
    navController: NavController,
    innerPaddingValues: PaddingValues
) {
    val context=LocalContext.current
    var adminImage= painterResource(R.drawable.admin)
    var delta= painterResource(R.drawable.delta)
    var dc180= painterResource(R.drawable.dc180)
    var graphique= painterResource(R.drawable.graphique)
    var spider= painterResource(R.drawable.spider)
    var festember=painterResource(R.drawable.festember)
    var pragyan=painterResource(R.drawable.pragyan)
    var recall=painterResource(R.drawable.recall)
    var forum=painterResource(R.drawable.group)
    var nittfest=painterResource(R.drawable.nittfest)
    var scrollState = rememberScrollState()
    val userDetails = getUserDetails(context = context)
    val showSideBar = remember { mutableStateOf(false)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "alma",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 36.sp,
                    color = Color(150,103,224)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text="mater",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 36.sp,
                    color = Color(188, 128, 240),
                    lineHeight = 30.sp
                )
            }
            Text(
                text="made for NIT Trichy",
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontSize = 14.sp,
                color = Color(150,103,224)
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(20.dp, 10.dp)
                        .shadow(15.dp, shape = RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                        val name = userDetails?.name
                        Column() {
                            if (name != null) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(top = 10.dp),
                                    text = "Hello $name !",
                                    fontFamily = FontFamily(Font(R.font.urbanist)),
                                    fontSize = 22.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                )
                            }
                            Card(
                                modifier = Modifier
                                    .padding(10.dp,10.dp)
                                    .clickable{
                                        navController.navigate(Screens.AdminPage.route)
                                    }
                                    .border(
                                        2.dp,
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color.Black
                                    ),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(177, 157, 255))
                            ) {
                                Row(modifier = Modifier.fillMaxWidth().padding(0.dp,5.dp),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Image(
                                        painter=adminImage,
                                        contentDescription="admin",
                                        modifier=Modifier.size(
                                           30.dp
                                        )
                                    )
                                    Text(
                                        text="Admin Dashboard",
                                        fontWeight=FontWeight.Bold,
                                        fontSize=24.sp,
                                        fontFamily=FontFamily(Font(R.font.urbanist))
                                    )

                                }
                            }
                        }
                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Explore",
                    fontWeight = FontWeight(600),
                    fontFamily = urbanist,
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Text(
                    text = "what's happening around you!",
                    fontWeight = FontWeight(500),
                    fontFamily = urbanist,
                    fontSize = 14.sp,
                    color = Color(177, 157, 255),
                    modifier= Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround)
                {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screens.ClubCommunityPage.route)
                                    // sai
                                    Toast
                                        .makeText(context, "Releasing soon....", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                .shadow(15.dp, shape = RoundedCornerShape(20.dp))
                                .shadow(10.dp, shape = RoundedCornerShape(20.dp))
                                .size(160.dp,170.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "Club",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = urbanist,
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 20.sp,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                                Text(
                                    text = "Community",
                                    fontWeight = FontWeight(600),
                                    fontFamily = urbanist,
                                    fontSize = 12.sp,
                                    color = Color(177, 157, 255),
                                    textAlign = TextAlign.Center,
                                )
                                Row(
                                    modifier= Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Image(
                                        painter = delta,
                                        contentDescription = "delta",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(
                                                40.dp
                                            )
                                    )
                                    Image(
                                        painter = dc180,
                                        contentDescription = "180DC",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(
                                                40.dp
                                            )
                                    )
                                }
                                Row(
                                    modifier= Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Image(
                                        painter = graphique,
                                        contentDescription = "graphique",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(
                                                40.dp
                                            )
                                    )
                                    Image(
                                        painter = spider,
                                        contentDescription = "Spider",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(
                                                50.dp
                                            )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                            Button(
                                modifier = Modifier.padding(top = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        rgb(
                                            163,
                                            127,
                                            219
                                        )
                                    )
                                ),
                                onClick = { navController.navigate(Screens.ClubDirectory.route)
                                    Toast.makeText(context,"Releasing soon....",Toast.LENGTH_SHORT).show()
                                          },
                            ) {
                                Text(text = "Club Directory",
                                    fontWeight = FontWeight(600),
                                    fontFamily = FontFamily(Font(R.font.urbanist)),
                                    fontSize = 16.sp,
                                    color = Color.White)
                            }
                    }
                   // the fest one
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .shadow(10.dp, shape = RoundedCornerShape(20.dp))
                                .shadow(15.dp, shape = RoundedCornerShape(20.dp))
                                .size(160.dp,170.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.padding(top = 7.dp))
                                Text(
                                    text = "Fest",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = urbanist,
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 20.sp
                                )
                                Text(
                                    text = "Community",
                                    fontWeight = FontWeight(600),
                                    fontFamily = urbanist,
                                    fontSize = 12.sp,
                                    color = Color(177, 157, 255),
                                    textAlign = TextAlign.Center
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Image(
                                        painter = festember,
                                        contentDescription = "festember",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .size(
                                                40.dp
                                            )
                                    )
                                    Image(
                                        painter = nittfest,
                                        contentDescription = "nittfest",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(
                                                40.dp
                                            )
                                    )
                                }
                                Image(
                                    painter = painterResource(R.drawable.pragyan),
                                    contentDescription = "pragyan",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(
                                            height = 40.dp, width = 100.dp
                                        )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Button(
                            modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    rgb(
                                        163,
                                        127,
                                        219
                                    )
                                )
                            ),
                            onClick = {navController.navigate(Screens.FestDirectory.route )},
                        ) {
                            Text(text = "Fest Directory",
                                fontWeight = FontWeight(600),
                                fontFamily = FontFamily(Font(R.font.urbanist)),
                                fontSize = 16.sp,
                                color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .shadow(15.dp, shape = RoundedCornerShape(20.dp))
                            .clickable {
                                navController.navigate(Screens.AlumniCommunityScreen.route)
                            }
                            .size(160.dp,170.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Alumni",
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = urbanist,
                                fontSize = 24.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = "Community",
                                fontWeight = FontWeight(600),
                                fontFamily = urbanist,
                                fontSize = 12.sp,
                                color = Color(177, 157, 255),
                                textAlign = TextAlign.Center,
                            )
                            Image(
                                painter = painterResource(R.drawable.alumni_logo),
                                contentDescription = "forum",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(110.dp,90.dp)
                            )
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .shadow(15.dp, shape = RoundedCornerShape(20.dp))
                            .clickable {
                                navController.navigate(Screens.CommunityPage.route)
                            }
                            .size(160.dp,170.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Student",
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = urbanist,
                                fontSize = 24.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = "Community",
                                fontWeight = FontWeight(600),
                                fontFamily = urbanist,
                                fontSize = 12.sp,
                                color = Color(177, 157, 255),
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.padding(top = 15.dp))
                            Image(
                                painter = painterResource(R.drawable.student_community),
                                contentDescription = "forum",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(60.dp,60.dp)
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp , end = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            IconButton(
                onClick = {
                    showSideBar.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Sidebar",
                    tint = Color.Black,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 10.dp)
                        .scale(1.2f)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            BottomBar(navController = navController)
        }
        if(showSideBar.value) {
            SideBar(showSideBar, navController)
        }
    }
}