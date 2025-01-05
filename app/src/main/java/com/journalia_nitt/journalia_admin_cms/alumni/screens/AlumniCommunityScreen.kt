package com.journalia_nitt.journalia_admin_cms.alumni.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.clickedPost
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniUpload
import com.journalia_nitt.journalia_admin_cms.alumni.response.Upvote
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniUploadViewModel
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AlumniCommunityScreen(navController: NavController)
{
    var searchText by remember { mutableStateOf("") }
    val viewModel: AlumniUploadViewModel = viewModel()
    val uploads = remember { viewModel.uploads }
    LaunchedEffect(Unit) {
        viewModel.fetchAllUploads()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 25.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier= Modifier
            .fillMaxWidth()
            .height(190.dp),
            contentAlignment = Alignment.Center){
            Column(
                modifier= Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Trending posts",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = urbanist,
                    )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .width(377.dp)
                        .height(130.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(16.dp),
                            spotColor = Color.Black,
                            ambientColor = Color.Black
                        )
                        .clickable {
                            println(uploads)
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(163, 127, 219)
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Top Opportunities of the day",
                            color = Color.Black,
                            fontFamily= urbanist,
                            fontWeight = FontWeight(600),
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Zomato is Hiring!",
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily= urbanist
                        )
                        Text(
                            text = "20L-30LPA",
                            color = Color.Black,
                            fontFamily = urbanist,
                            fontSize = 27.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.width(140.dp))
            Box(){

                Button(
                    onClick = { /* Handle sort */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(12.dp))
                        .shadow(10.dp, shape = RoundedCornerShape(12.dp))
                        .size(height = 40.dp, width = 100.dp)
                ) {
                    Text(
                        text="     Sort",
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = urbanist
                    )
                }
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .offset(x = 8.dp, y = 5.dp),
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = "sort",
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Black)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(uploads) { upload ->
                if(upload.file_url.isNullOrEmpty()) {
                    NoImageCardForAlumni(navController, upload)
                }
                else {
                    ImageCardForAlumni(navController, upload)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun NoImageCardForAlumni(
    navController: NavController,
    upload: AlumniUpload
) {
    Card(
        modifier = Modifier
            .width(376.dp)
            .padding(6.dp)
            .height(118.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color.Black,
                ambientColor = Color.Black
            )
            .clickable {
                clickedPost.value = upload
                navController.navigate(Screens.AlumniPostViewScreen.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(251, 251, 251))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    modifier = Modifier,
                    text = upload.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontFamily = urbanist,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    modifier = Modifier,
                    text = upload.description,
                    fontWeight = FontWeight.W400, // Use the W400 font weight
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start, // Align text to the start (left-aligned)
                    fontFamily = urbanist,
                    maxLines = 2, // Limit text to 2 lines
                    overflow = TextOverflow.Ellipsis, // Add ellipsis for overflowing text
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.padding(start = 30.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.scale(3f),
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Profile"
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = upload.username,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.offset(y=6.dp)
                        )
                        Text(
                            text = "apple ceo",
                            fontSize = 12.sp,
                            modifier = Modifier.offset(y=(-6).dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(18.dp)
                                .offset(y = 4.dp),
                            painter = painterResource(id = R.drawable.upvoteempty),
                            contentDescription = "Profile"
                        )
                        Text(
                            text = upload.upvotes.toString(),
                            fontSize = 11.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 25.dp))
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(20.dp),
                            painter = painterResource(id = R.drawable.comment_button),
                            contentDescription = "comment"
                        )
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text(
                            text = upload.comments.size.toString(),
                            fontSize = 11.sp,
                            fontFamily = urbanist,
                            modifier = Modifier
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                }
            }
        }
    }
}

@Composable
fun ImageCardForAlumni(
    navController: NavController,
    upload: AlumniUpload
) {
    val viewModel: AlumniUploadViewModel = viewModel()
    Card(
        modifier = Modifier
            .width(376.dp)
            .padding(6.dp)
            .height(338.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color.Black,
                ambientColor = Color.Black
            )
            .clickable {
                clickedPost.value = upload
                navController.navigate(Screens.AlumniPostViewScreen.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(251, 251, 251))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    modifier = Modifier,
                    text = upload.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontFamily = urbanist,
                    maxLines = 1, // Limit text to 2 lines
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    modifier = Modifier,
                    text = upload.description,
                    fontWeight = FontWeight.W400, // Use the W400 font weight
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start, // Align text to the start (left-aligned)
                    fontFamily = urbanist,
                    maxLines = 2, // Limit text to 2 lines
                    overflow = TextOverflow.Ellipsis, // Add ellipsis for overflowing text
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(200.dp)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                DisplayPostImage(upload.file_url)
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.padding(start = 30.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.scale(3f),
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Profile"
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = upload.username,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.offset(y=6.dp)
                        )
                        Text(
                            text = "apple ceo",
                            fontSize = 12.sp,
                            modifier = Modifier.offset(y=(-6).dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        var image = remember{mutableStateOf( R.drawable.upvoteempty)}
                        if(upload.upvoters.contains(theUser.username)) {
                            image.value = R.drawable.triangle
                        }
                        var upvotes = remember(upload.upvotes){mutableStateOf(upload.upvotes)}
                        Image(
                            modifier = Modifier
                                .size(18.dp)
                                .offset(y = 4.dp)
                                .clickable {
                                    if(!upload.upvoters.contains(theUser.username)) {
                                        viewModel.upvotePost(
                                            upload.id,
                                            Upvote(theUser.username, upload.id)
                                        )
                                        upload.upvotes++
                                        image.value = R.drawable.triangle
                                    }
                                },
                            painter = painterResource(id = image.value),
                            contentDescription = "Profile"
                        )
                        Text(
                            text = upvotes.value.toString(),
                            fontSize = 11.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 25.dp))
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(20.dp),
                            painter = painterResource(id = R.drawable.comment_button),
                            contentDescription = "comment"
                        )
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text(
                            text = upload.comments.size.toString(),
                            fontSize = 11.sp,
                            fontFamily = urbanist,
                            modifier = Modifier
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                }
            }
        }
    }
}