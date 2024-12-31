package com.journalia_nitt.journalia_admin_cms.alumni.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun ContactUs(
    navController: NavController,
    innerPaddingValues: PaddingValues
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_button_navigation),
                    contentDescription = "Back button",
                    modifier = Modifier
                        .scale(2f)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }

            Spacer(modifier = Modifier.width(80.dp))

            Text(
                text = "Contact Us",
                modifier = Modifier.align(Alignment.CenterVertically),
                fontFamily = urbanist,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y=132.dp)
        ){

            Card(
                modifier = Modifier
                    .width(359.dp)
                    .height(113.dp)
                    .offset(y=50.dp)
                    .shadow(
                        elevation = 6.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp
                        )
                    )
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(150, 103, 224, 255), // rgba(150, 103, 224, 1)
                                Color(188, 128, 240, 255)  // rgba(188, 128, 240, 1)
                            )
                        )
                    ),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            ) {
            }
            Card(
                modifier = Modifier
                    .width(359.dp)
                    .height(62.dp)
                    .shadow(
                        elevation = 6.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp
                        )
                    ),
                colors = CardDefaults.cardColors(Color(247, 247, 247)),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                )
            ) {
            }
        }
    }
}