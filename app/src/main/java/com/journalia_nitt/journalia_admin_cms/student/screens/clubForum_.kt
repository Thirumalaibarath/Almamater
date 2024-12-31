package com.journalia_nitt.journalia_admin_cms.student.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.student.viewModels.FetchViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist


@Composable
fun clubCommunityPage(
    innerPadding: PaddingValues,
    navController: NavController
){
    val userFetch: FetchViewModel =viewModel()
    LaunchedEffect(Unit) {
        userFetch.fetchAll()
    }
    val posts=userFetch.posts1.value.data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Trending now",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontFamily = urbanist
        )
        Spacer(modifier = Modifier.height(23.dp))
        val trendingPosts = listOf(
            setOf(Uri.parse("android.resource://com.example.journalia/drawable/scene"), "what if we all die without knowing we died? Isn't that concerning", "Aatman patel"),
            setOf(null, "what if we all die without knowing we died? Isn't that concerning", "Aatman patel"),
            setOf(null, "what if we all die without knowing we died? Isn't that concerning", "Aatman patel"),
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(122.dp)
        ) {
            items(trendingPosts) { post ->
                val image = post.elementAtOrNull(0) as? Uri
                val content = post.elementAtOrNull(1) as? String ?: ""
                val author = post.elementAtOrNull(2) as? String ?: ""

                trendingCardClub(
                    img = image,
                    content = content,
                    author = author
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(39.82.dp)
        ){
            Spacer(modifier = Modifier.width(19.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(101.dp)
                        .height((38.82).dp),
                    shape = RoundedCornerShape((9.71).dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffA37FDB)
                    )
                ) {
                }
                Text(
                    text = "Sort",
                    fontSize = (16.18).sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-17.dp)),
                    fontFamily = urbanist,
                    color = Color.White
                )
                Image(
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = "sort",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 17.dp)
                        .size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(19.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height((38.82).dp),
                    shape = RoundedCornerShape((9.71).dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffA37FDB)
                    )
                ) {
                }
                Text(
                    text = "Category",
                    fontSize = (16.18).sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .align(Alignment.Center),
                    fontFamily = urbanist,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(19.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(110.dp)
                        .height((38.82).dp),
                    shape = RoundedCornerShape((9.71).dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffA37FDB)
                    )
                ) {
                }
                Text(
                    text = "Filter",
                    fontSize = (16.18).sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-17.dp)),
                    fontFamily = urbanist,
                    color = Color.White
                )
                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "filter",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 17.dp)
                        .size(26.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(19.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(posts.chunked(2)) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    pair.forEach { post ->
                        Box(
                            modifier = Modifier
                                .weight(1f) // Distributes width equally
                                .padding(4.dp)
                        ) {
                            if (post.title!="") {
                                ImagelessCard(post, Color.White,navController)
                            } else {
                                ImageCard(post, Color.White,navController)
                            }
                        }
                    }

                    // Add an empty box to fill remaining space if the pair is incomplete
                    if (pair.size == 1) {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun trendingCardClub(
    img: Uri? = null,
    content: String,
    author: String = "By Anonymous",
){
    if (img == null){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(122.dp)
                .width(259.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(16.dp)
        ){

            Column(
                modifier = Modifier.fillMaxHeight()
                    .padding(start = 10.dp, end=10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = "Top Post Of the day",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Add spacing around the line
                    color = Color.Gray, // Customize the color of the line
                    thickness = 1.dp // Customize the thickness of the line
                )
                Text(
                    modifier = Modifier,
                    text = content,
                    fontSize = 13.09.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = urbanist,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .offset(x=60.dp),
                    text = "- $author",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = urbanist,
                    color = Color.Gray
                )
            }
        }
    }
    else{
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(122.dp)
                .width(259.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(end=4.dp).offset(x=(-13).dp)){
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.667f)
                        .align(Alignment.CenterStart)
                ){
                    val painter: Painter = rememberAsyncImagePainter(model = img)
                    Image(
                        painter = painter,
                        contentDescription = "Trending Image",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.333f)
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(y=5.dp),
                        text = content,
                        fontSize = 13.09.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = urbanist,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y=(-10).dp),
                        text = "- $author",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = urbanist,
                        color = Color.Gray ,
                        lineHeight = 12.sp
                    )
                }
            }
        }
    }
}