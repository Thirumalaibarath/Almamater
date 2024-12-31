package com.journalia_nitt.journalia_admin_cms.common.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.responses.Deadline
import com.journalia_nitt.journalia_admin_cms.student.screens.getMonth
import com.journalia_nitt.journalia_admin_cms.student.viewModels.DeadlineViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AdminComponents(navController: NavController) {
    val mode = remember { mutableIntStateOf(0) }

    val deadlineViewModel: DeadlineViewModel = viewModel()
    LaunchedEffect(Unit) {
        deadlineViewModel.fetchDeadlines()
    }
    val deadlines = deadlineViewModel.deadlines.collectAsState()
    val error = deadlineViewModel.error.collectAsState()

    if(error.value != null) {
        Log.d("Error" , error.value.toString())
    }
    else {
        Log.d("Deadlines in calender page", deadlines.value.toString())
    }
    val color1 = Color(163,127,219)
    val color2 = Color(205,193,255)
    val colorOfFirstCard = remember(mode.intValue) {
        if(mode.intValue == 0) {
            mutableStateOf(color1)
        }
        else {
            mutableStateOf(color2)
        }
    }
    val colorOfSecondCard = remember(mode.intValue) {
        if(mode.intValue == 1) {
            mutableStateOf(color1)
        }
        else {
            mutableStateOf(color2)
        }
    }
    var query by remember { mutableStateOf("") }
    val filteredPosts = remember(query, deadlines.value) {
        if (query.isBlank()) deadlines.value
        else deadlines.value.filter {
            it.title?.contains(query, ignoreCase = true) == true || it.author.contains(query, ignoreCase = true)
        }
    }
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                value = query,
                singleLine = true,
                onValueChange = {
                    query = it
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                placeholder = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Search for posts, deadlines and info",
                            fontSize = 16.sp,
                            fontFamily = urbanist
                        )
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if(mode.intValue != 0) {
                        mode.intValue = 0
                    }
                },
                modifier = Modifier.size(170.dp, 50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorOfFirstCard.value)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "DEADLINE",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = urbanist
                    )
                }
            }
            Button(
                onClick = {
                    if(mode.intValue != 1) {
                        mode.intValue = 1
                    }
                },
                modifier = Modifier.size(180.dp, 50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorOfSecondCard.value)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "ANNOUNCEMENT",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = urbanist
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        LazyColumn() {
            items(filteredPosts) { item->
                Spacer(modifier = Modifier.padding(top = 10.dp))
                if(mode.intValue==item.mode)
                    AdminCard(item,navController)
            }
        }
    }
}
@Composable
fun AdminCard(
    item : Deadline,
    navController: NavController
) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable {
                navController.navigate(Screens.AdminDetailsPage.createRoute(item))
            }
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val title = when(item.title.toString().length) {
                in 0..70 -> item.title.toString()
                else -> item.title.toString().substring(0, 70) + "..."
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = urbanist
                )
            }
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "B.Tech All Years",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = urbanist
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = item.author,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = urbanist
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val month = getMonth(item.deadline.substring(3,5).toInt())
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "Deadline : " + item.deadline.substring(0,2) + " " + month.lowercase() + " " + item.deadline.substring(6,10),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = urbanist
                )
            }
        }
    }
}