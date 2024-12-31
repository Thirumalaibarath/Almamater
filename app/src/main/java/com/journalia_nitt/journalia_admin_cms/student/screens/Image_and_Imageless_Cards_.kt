package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.responses.UserFetchClass
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun ImagelessCard(
    item: UserFetchClass,
    color: Color,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                navController.navigate(Screens.ViewPost.createRoute(item))
            }
            .padding(horizontal = 10.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (item.description.toString().length <= 50) {
                    Text(
                        text = item.description.toString(),
                        fontFamily = urbanist,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 14.sp
                    )
                } else {
                    Text(
                        text = item.description.toString().substring(0, 50) + "...",
                        fontFamily = urbanist,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 14.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box() {
                    Image(
                        painter = painterResource(R.drawable.like),
                        contentDescription = null,
                        modifier = Modifier.scale(2.5f)
                    )
                    Text(
                        text = item.upvotes.toString(),
                        fontFamily = urbanist,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.comment),
                    contentDescription = null,
                    modifier = Modifier.scale(2.5f)
                )
                Image(
                    painter = painterResource(R.drawable.forward),
                    contentDescription = null,
                    modifier = Modifier.scale(2.5f)
                )
            }
        }
    }
}

@Composable
fun ImageCard(
    item: UserFetchClass,
    color: Color,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(250.dp)
            .clickable{
                navController.navigate(Screens.ViewPost.createRoute(item))
            },
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    painter = rememberAsyncImagePainter(item.fileUrl.toString()),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (item.description.toString().length <= 50) {
                        Text(
                            text = item.description.toString(),
                            fontFamily = urbanist,
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontSize = 14.sp
                        )
                    } else {
                        Text(
                            text = item.description.toString().substring(0, 50) + "...",
                            fontFamily = urbanist,
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier.scale(2.5f)
                )
                Image(
                    painter = painterResource(R.drawable.comment),
                    contentDescription = null,
                    modifier = Modifier.scale(2.5f)
                )
                Image(
                    painter = painterResource(R.drawable.forward),
                    contentDescription = null,
                    modifier = Modifier.scale(2.5f)
                )
            }
        }
    }
}