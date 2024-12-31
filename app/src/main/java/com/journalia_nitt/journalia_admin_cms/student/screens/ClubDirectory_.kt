package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens

@Composable
fun ClubSubPage(navController: NavController) {
    val searchText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier=Modifier
            .fillMaxWidth()
            .height(100.dp),
            contentAlignment = Alignment.Center
        ){
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .width(350.dp),
                shape = RoundedCornerShape(20.dp),
                value = searchText.value,
                onValueChange = { searchText.value = it},
                placeholder = {
                    Text(
                        text = "Search for Clubs",
                        fontWeight = FontWeight(100),
                        fontFamily = FontFamily(Font(R.font.urbanist))
                        )
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            // Dividing the items into rows of 2
            items((0..9).chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { _ ->
                        Card(
                            modifier = Modifier
                                .width(150.dp)// Ensures equal spacing
                                .height(160.dp)
                                .shadow(10.dp, shape = RoundedCornerShape(20.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color(163, 127, 219))
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .padding(10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.club1),
                                        contentDescription = "Product Folk",
                                        modifier = Modifier.size(
                                            height = 70.dp,
                                            width = 120.dp
                                        )
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .width(50.dp)
                                    ) {
                                        Button(
                                            onClick = {navController.navigate(Screens.ClubPage.route)},
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(217, 217, 217)
                                            ),
                                            shape = CircleShape,
                                            modifier = Modifier.size(50.dp)
                                        ) {}
                                        Icon(
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .size(30.dp),
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "User",
                                            tint = Color.Black
                                        )
                                    }
                                    Card(
                                        modifier = Modifier.size(
                                            height = 100.dp,
                                            width = 95.dp
                                        )
                                            .offset(x = 10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(205, 193, 255)
                                        )
                                    ) {
                                        Box(
                                            Modifier
                                                .fillMaxSize()
                                                .padding(start = 10.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Product Management",
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight(600),
                                                fontFamily = FontFamily(Font(R.font.urbanist)),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}