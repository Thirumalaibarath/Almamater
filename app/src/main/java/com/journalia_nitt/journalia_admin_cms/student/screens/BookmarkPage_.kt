package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.student.responses.BookMark
import com.journalia_nitt.journalia_admin_cms.student.viewModels.bookMarkViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun BookmarkPage() {

    val mode = remember { mutableIntStateOf(1) }
    val bookMarkViewModel: bookMarkViewModel = viewModel()
    LaunchedEffect(Unit) {
        bookMarkViewModel.fetchBookMark("111")
    }
    val posts = bookMarkViewModel.posts.value.data.details
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val color1 = Color(163,127,219)
            val color2 = Color(205,193,255)
            val colorOfFirstCard = remember(mode.intValue) {
                if(mode.intValue == 1) {
                    mutableStateOf(color1)
                }
                else {
                    mutableStateOf(color2)
                }
            }
            Card(
                modifier = Modifier
                    .size(150.dp, 60.dp)
                    .clickable {

                    },
                colors = CardDefaults.cardColors(colorOfFirstCard.value)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "ADMIN POSTS",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = urbanist
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        LazyColumn() {
            items(posts) { item ->
                Spacer(modifier = Modifier.padding(top = 10.dp))
                BookmarkCard(item)
            }
        }
    }
}

@Composable
fun BookmarkCard(
    item : BookMark
) {

    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .height(170.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = item.title.toString(),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
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
                        fontWeight = FontWeight.SemiBold
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
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            val description = when(item.description.toString().length) {
                in 0..100 -> item.description.toString()
                else -> item.description.toString().substring(0, 100) + "..."
            }
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 30.dp)
            ) {
                Image(
                    modifier = Modifier.scale(2f),
                    painter = painterResource(id = R.drawable.bookmark),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(start = 20.dp))
                if(description.length<=100) {
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                else {
                    Text(
                        text = description.substring(0, 100) + "...",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}