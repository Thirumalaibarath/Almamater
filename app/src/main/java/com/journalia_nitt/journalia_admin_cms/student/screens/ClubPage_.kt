package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R

@Composable

fun ClubPage(innerPadding :PaddingValues , navController: NavController){
    var clubLogo= painterResource(R.drawable.product)
    var side=painterResource(R.drawable.side)
    var add=painterResource(R.drawable.add)
    var head=painterResource(R.drawable.dummy)
    var scrollState= rememberScrollState()
    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )
    Column (modifier = Modifier
            .fillMaxHeight()
            .padding(innerPadding)
            .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .size(
                            height = 123.dp,
                            width = 338.dp
                        )
                        .shadow(10.dp, shape = RoundedCornerShape(20.dp)),
                    colors= CardDefaults.cardColors(containerColor = Color(150, 103, 224)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(modifier= Modifier
                        .width(348.dp)
                        .background(gradient)
                        .padding(start=30.dp,top=25.dp)){
                        Image(
                            modifier = Modifier
                                .size(
                                    height = 77.dp,
                                    width= 152.dp
                                ),
                            painter = clubLogo,
                            contentDescription = "Club Logo",
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Row(modifier = Modifier
                            .padding(top=20.dp)
                            .fillMaxSize()) {
                            Button(
                                modifier = Modifier
                                    .size(
                                        width = 114.dp,
                                        height = 40.dp
                                    ),
                                colors = ButtonDefaults.buttonColors(Color.White),
                                onClick = {}) {
                                Text(
                                    text = "Follow",
                                    color = Color.Black,
                                    fontSize=15.sp,
                                    fontWeight = FontWeight(600)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = add,
                                    contentDescription = "Add",
                                    modifier = Modifier
                                        .size(
                                            width = 50.dp,
                                            height = 50.dp
                                        )
                                )
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.padding(top=15.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .height(155.dp)
                        .width(350.dp),
                        contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "The Product Folks",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(Font(R.font.poppins))
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            textAlign = TextAlign.Justify,
                            text = "Ahh it’s the worst place you can be at, Firstly the climate here is horrible and don’t even get me started at the mess food. The minute you take a bite in, you will feel like puking...",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            fontFamily = FontFamily(Font(R.font.poppins))
                        )
                    }
                }
            }

            Box(contentAlignment = Alignment.TopCenter,
                modifier= Modifier
                    .width(400.dp)
                    .height(160.dp)
                    .padding(top=0.dp)
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Heads",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        fontFamily = FontFamily(Font(R.font.urbanist))

                    )
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    Row(
                        modifier=Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column{
                            Image(
                                painter = head,
                                contentDescription = "Head",
                                modifier = Modifier
                                    .size(
                                        width = 75.dp,
                                        height = 75.dp
                                    )
                                    .shadow(20.dp, shape = RoundedCornerShape(20.dp))
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 13.dp, top = 2.dp),
                                text = "Head1",
                                fontSize = 15.sp,
                                fontWeight = FontWeight(400),
                                fontFamily = FontFamily(Font(R.font.urbanist)),
                                color = Color.Black,

                                )
                        }

                        Column{
                            Image(
                                painter = head,
                                contentDescription = "Head",
                                modifier = Modifier
                                    .size(
                                        width = 75.dp,
                                        height = 75.dp
                                    )
                                    .shadow(20.dp, shape = RoundedCornerShape(20.dp))
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 13.dp, top = 2.dp),
                                text = "Head1",
                                fontSize = 15.sp,
                                fontWeight = FontWeight(400),
                                fontFamily = FontFamily(Font(R.font.urbanist)),
                                color = Color.Black,

                                )
                        }
                        Column{
                            Image(
                                painter = head,
                                contentDescription = "Head",
                                modifier = Modifier
                                    .size(
                                        width = 75.dp,
                                        height = 75.dp
                                    )
                                    .shadow(20.dp, shape = RoundedCornerShape(20.dp))
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 13.dp, top = 2.dp),
                                text = "Head1",
                                fontSize = 15.sp,
                                fontWeight = FontWeight(400),
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                color = Color.Black,
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier
                .width(400.dp)
                .height(230.dp)
                .padding(start=10.dp)){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "CLUB WORK & ACHIVEMENTS",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        fontFamily = FontFamily(Font(R.font.urbanist)),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .size(
                                height = 80.dp,
                                width = 400.dp
                            )
                            .shadow(10.dp, shape = RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(modifier = Modifier
                            .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painter=painterResource(R.drawable.pivot),
                                contentDescription = "pivot",
                                modifier=Modifier
                                    .size(
                                        height=50.dp,
                                        width=50.dp
                                    )
                            )
                            Column(){
                                Text(
                                    text="PIVOT",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(700),
                                    fontFamily = FontFamily(Font(R.font.urbanist))
                                )
                                Text(
                                    text="Our consulting Magazine is out!",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    fontFamily = FontFamily(Font(R.font.urbanist))
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(50.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Button(
                                    onClick = {},
                                    modifier = Modifier
                                        .size(50.dp),
                                    colors = ButtonDefaults.buttonColors(Color(217, 217, 217)),
                                ) {

                                }
                                Image(
                                    painter = side,
                                    contentDescription = "side",
                                    modifier = Modifier
                                        .size(25.dp),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .size(
                                height = 80.dp,
                                width = 400.dp
                            )
                            .shadow(10.dp, shape = RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(modifier = Modifier
                            .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painter=painterResource(R.drawable.pivot),
                                contentDescription = "pivot",
                                modifier=Modifier
                                    .size(
                                        height=50.dp,
                                        width=50.dp
                                    )
                            )
                            Column(){
                                Text(
                                    text="PIVOT",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(700),
                                    fontFamily = FontFamily(Font(R.font.urbanist))
                                )
                                Text(
                                    text="Our consulting Magazine is out!",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    fontFamily = FontFamily(Font(R.font.urbanist))
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(50.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Button(
                                    onClick = {},
                                    modifier = Modifier
                                        .size(50.dp),
                                    colors = ButtonDefaults.buttonColors(Color(217, 217, 217)),
                                ) {

                                }
                                Image(
                                    painter = side,
                                    contentDescription = "side",
                                    modifier = Modifier
                                        .size(25.dp),
                                )
                            }
                        }

                    }
                }

            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
                contentAlignment = Alignment.Center
            ){
                Button(onClick = {},
                    modifier = Modifier
                        .width(155.dp)
                        .height(50.dp),
                    colors=ButtonDefaults.buttonColors(Color(163, 127, 219)),
                    ){
                    Text(
                        text = "Click for more",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(R.font.urbanist))
                    )
                }
            }

     }
}
