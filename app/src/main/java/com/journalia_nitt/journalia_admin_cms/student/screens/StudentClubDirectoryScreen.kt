package com.journalia_nitt.journalia_admin_cms.student.screens

import android.net.Uri
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.student.viewModels.FetchViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun StudentClubDirectoryScreen(navController: NavController)
{
    val searchText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier= Modifier
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
                                            onClick = {
//                                                navController.navigate(Screens.ClubPage.route)
                                                      },
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
@Composable
fun clubCommunityPage(
    innerPadding: PaddingValues,
    navController: NavController
){
    val userFetch: FetchViewModel = viewModel()
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