package com.example.journalia.Student

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.journalia.Navigation.urbanist
import com.example.journalia.R
import com.example.journalia.Student.ViewModels.FetchViewModel

val family = FontFamily(
    Font(R.font.urbanist)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityPage(
    innerPadding: PaddingValues,
    navController: NavController
){
    val userFetch: FetchViewModel =viewModel()
    LaunchedEffect(Unit) {
        userFetch.fetchAll()
    }
    val posts=userFetch.posts1.value.data
    var query by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf("") }
    val filteredPosts = remember(query, selectedTag,posts) {
        if (query.isBlank() && selectedTag.isBlank()) posts
        else posts.filter {
                    if(!query.isBlank() ) {
                        if(selectedTag.isBlank()) {
                            it.title.contains(query, ignoreCase = true) ||
                                    it.description.contains(query, ignoreCase = true)
                        }
                        else{
                            (it.title.contains(query, ignoreCase = true) ||
                                    it.description.contains(query, ignoreCase = true) )&&
                                    it.tag.equals(selectedTag, ignoreCase = true)
                        }
                    }
            else {
                        it.tag.equals(selectedTag, ignoreCase = true)
                    }

        }
    }
    var showDropForFilter by remember { mutableStateOf(false) }
    var showDropForSort by remember { mutableStateOf(false) }
    var showDropForCategory by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Search for posts, deadlines and info",
                            fontSize = 18.sp,
                            fontFamily = urbanist
                        )
                        Spacer(modifier = Modifier.padding(start = 10.dp))
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Trending now",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontFamily = family
        )
        Spacer(modifier = Modifier.height(23.dp))
        val trendingPosts = listOf(
            setOf(
                Uri.parse("android.resource://com.example.journalia/drawable/scene"),
                "what if we all die without knowing we died? Isn't that concerning",
                "Aatman patel"
            ),
            setOf(
                null,
                "what if we all die without knowing we died? Isn't that concerning",
                "Aatman patel"
            ),
            setOf(
                null,
                "what if we all die without knowing we died? Isn't that concerning",
                "Aatman patel"
            ),
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

                trendingCard(
                    img = image,
                    content = content,
                    author = author
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(39.82.dp)
        ) {
            Spacer(modifier = Modifier.width(19.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Button(
                    onClick = {
                        showDropForSort = !showDropForSort
                    },
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
                    fontFamily = family,
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
                val dropdown = listOf("Recent","Most liked","Popular")
                val gradient = Brush.linearGradient(
                    colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
                )
                DropdownMenu(
                    expanded = showDropForSort,
                    onDismissRequest = {
                        showDropForSort = false
                    },
                    modifier = Modifier
                        .size(100.dp, 120.dp)
                        .background(gradient)
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(gradient),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            for (i in 0..2) {
                                Text(
                                    text = dropdown[i],
                                    fontFamily = urbanist,
                                    modifier = Modifier
                                        .clickable {
                                            showDropForSort = false
                                            selectedTag = dropdown[i]
                                        },
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight(500),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.padding(top = 5.dp))
                                if(i!=2) {
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 15.dp),
                                        color = Color.White,
                                        thickness = 1.dp
                                    )
                                    Spacer(modifier = Modifier.padding(top = 5.dp))
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(19.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Button(
                    onClick = {
                        showDropForCategory = !showDropForCategory
                    },
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
                    fontFamily = family,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(19.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Button(
                    onClick = {
                        showDropForFilter = !showDropForFilter
                    },
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
                    fontFamily = family,
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
                val dropdown = listOf("Memes","Hostel","Admin","College")
                val gradient = Brush.linearGradient(
                    colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
                )
                DropdownMenu(
                    expanded = showDropForFilter,
                    onDismissRequest = {
                        showDropForFilter = false
                    },
                    modifier = Modifier
                        .size(110.dp, 150.dp)
                        .background(gradient)
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(gradient),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            for (i in 0..3) {
                                Text(
                                    text = dropdown[i],
                                    fontFamily = urbanist,
                                    modifier = Modifier
                                        .clickable {
                                            showDropForFilter = false
                                            selectedTag = dropdown[i]
                                        },
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight(500),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.padding(top = 5.dp))
                                if(i!=3) {
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 15.dp),
                                        color = Color.White,
                                        thickness = 1.dp
                                    )
                                    Spacer(modifier = Modifier.padding(top = 5.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(19.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredPosts.chunked(2)) { pair ->
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
                            if (post.fileUrl == "") {
                                ImagelessCard(post, Color.White, navController)
                            } else {
                                ImageCard(post, Color.White, navController)
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
    if(showDropForCategory) {
        AlertDialog(
            onDismissRequest = { showDropForCategory = false },
            modifier = Modifier.size(180.dp,250.dp)
        ) {
            val dropdown = listOf("Clubs","Fests","Public","RECAL")
            val gradient = Brush.linearGradient(
                colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
            )
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    for (i in 0..3) {
                        Row(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .clickable {
                                    showDropForCategory = false
                                    selectedTag = dropdown[i]
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val resourceId = when (i) {
                                0 -> R.drawable.clubs
                                1 -> R.drawable.fests
                                2 -> R.drawable.forum
                                3 -> R.drawable.recall
                                else -> 0
                            }
                            val scale = when (i) {
                                0 -> 2
                                1 -> 2
                                2 -> 1
                                3 -> 1
                                else -> 0
                            }
                            Image(
                                painter = painterResource(id = resourceId),
                                contentDescription = "filter",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .scale(scale.toFloat())
                            )
                            Spacer(modifier = Modifier.padding(start = 15.dp))
                            Text(
                                text = dropdown[i],
                                fontFamily = urbanist,
                                modifier = Modifier,
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(500),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                        if(i!=3) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                color = Color.Black,
                                thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.padding(top = 5.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun trendingCard(
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
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp, end = 10.dp),
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
                    fontFamily = family,
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
                    fontFamily = family,
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
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(end = 4.dp)
                .offset(x = (-13).dp)){
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
                            .offset(y = 5.dp),
                        text = content,
                        fontSize = 13.09.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = family,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = (-10).dp),
                        text = "- $author",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = family,
                        color = Color.Gray ,
                        lineHeight = 12.sp
                    )
                }
            }
        }
    }
}