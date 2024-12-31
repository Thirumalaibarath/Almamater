package com.journalia_nitt.journalia_admin_cms.alumni.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniUploadViewModel
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.EditState
import com.journalia_nitt.journalia_admin_cms.alumni.clickedPost
import com.journalia_nitt.journalia_admin_cms.alumni.response.Comment
import com.journalia_nitt.journalia_admin_cms.alumni.response.Upvote
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_alumni_cms
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun PageContent(innerPadding: PaddingValues,
                showBottomSheet: MutableState<Boolean>,
                navController: NavController
) {
    val scrollState = rememberScrollState()
    val heading = clickedPost.value.title
    val body = clickedPost.value.description
    val formLink = clickedPost.value.link1
    val viewModel: AlumniUploadViewModel = viewModel()

    val context = LocalContext.current
    val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pfp)
    val comments = remember {
        mutableStateListOf<Comment>().apply {
            addAll(clickedPost.value.comments)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(Alignment.Start),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(start = 10.dp)
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
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "POSTS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = urbanist
            )
        }
        Divider(
            color = Color.LightGray,
            thickness = (1.5).dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 15.dp)
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .scale(2.5f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "Profile image"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(start = 5.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ){
                Text(
                    text = clickedPost.value.username,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = urbanist
                )
                Text(
                    modifier = Modifier
                        .offset(y=18.dp),
                    text = "CEO - Bharatx",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    fontFamily = urbanist
                )
            }
            Spacer(modifier = Modifier.padding(start = 110.dp))
            if(clickedPost.value.username == theUser.username) {
                Card(
                    modifier = Modifier.size(95.dp, 45.dp),
                    colors = CardDefaults.cardColors(Color(163, 127, 219))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.padding(start = 10.dp))

                        Text(
                            text = "Edit ",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = urbanist
                        )
                        IconButton(
                            onClick = {
                                EditState.value = true
                                navController.navigate(Screens_in_alumni_cms.postPage.route)
                            },
                            modifier = Modifier.scale(2f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.edit_button),
                                contentDescription = "Edit button"
                            )
                        }
                    }
                }
            }
        }

        // Content Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = heading,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = urbanist,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 25.dp))
            Divider(
                color = Color.LightGray,
                thickness = (1).dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
            Box(
                modifier = Modifier
                    .width(353.dp)
                    .height(182.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(12.dp))
            ){
                DisplayPostImage(fileUrl = clickedPost.value.file_url)
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = body,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                fontSize = 16.sp,
                fontFamily = urbanist
            )
            if(clickedPost.value.link1.isNotEmpty()) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp)
                        .padding(horizontal = 30.dp),
                    colors = CardDefaults.cardColors(Color(163, 127, 219))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Available link",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontFamily = urbanist
                        )
                        IconButton(
                            onClick = {},
                            modifier = Modifier.scale(0.9f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.link),
                                contentDescription = "Link button"
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "3 mins ago",
                    fontSize = 12.sp,
                    fontFamily = urbanist
                )
            }
            Divider(
                color = Color.LightGray,
                thickness = (1).dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Box {
                    IconButton(
                        onClick = {
                            showBottomSheet.value = true
                        },
                        modifier = Modifier
                            .scale(2f)
                            .align(Alignment.TopCenter)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.comment_button),
                            contentDescription = "Comment button"
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = 30.dp),
                        text = clickedPost.value.comments.size.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = urbanist
                    )
                }
                var image = remember(clickedPost.value.upvoters) {mutableStateOf( R.drawable.upvoteempty)}
                if(clickedPost.value.upvoters.contains(theUser.username)) {
                    image.value = R.drawable.triangle
                }
                var upvotes = remember(clickedPost.value.upvotes){mutableStateOf(clickedPost.value.upvotes)}
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Box{
                    IconButton(
                        onClick = {
                            if(!clickedPost.value.upvoters.contains(theUser.username)) {
                                println(clickedPost.value.id)
                                viewModel.upvotePost(
                                    clickedPost.value.id,
                                    Upvote(theUser.username, clickedPost.value.id)
                                )
                                clickedPost.value.upvotes++
                                image.value = R.drawable.triangle
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                    ) {
                        Image(
                            painter = painterResource(id = image.value),
                            contentDescription = "Like button",
                            modifier = Modifier.size(23.dp)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = 31.dp),
                        text = upvotes.value.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = urbanist
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = 20.dp))
        // Comments Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                text = "Comments",
                fontSize = 19.sp,
                fontFamily = urbanist,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                text = clickedPost.value.comments.size.toString() + " comments",
                fontSize = 12.sp,
                fontFamily = urbanist,
                color = Color.Black,
                textDecoration = TextDecoration.Underline
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        if (comments.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                    text = "No comments yet",
                    fontSize = 16.sp,
                    fontFamily = urbanist,
                    color = Color.Gray
                )
            }
        } else {
            comments.forEach { comment ->
                CommentCard(comment, bitmap)
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
    }
}

@Composable
fun CommentListSection(comments: List<Comment>, bitmap: Bitmap) {
    Column {
        if (comments.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                text = "No comments yet",
                fontSize = 16.sp,
                fontFamily = urbanist,
                color = Color.LightGray
            )
        } else {
            LazyColumn {
                items(comments) { comment ->
                    CommentCard(comment, bitmap)
                }
            }
        }
    }
}

@Composable
fun CommentCard(comment: Comment, bitmap: Bitmap) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp),
        colors = CardDefaults.cardColors(Color(163, 127, 219)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = comment.username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
                Text(
                    text = comment.comment,
                    fontSize = 12.sp,
                    color = Color.White,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

@Composable
fun Page(innerPadding: PaddingValues , navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val showBottomSheet = remember { mutableStateOf(false) }
        if (showBottomSheet.value) {
            DraggableBottomSheet(innerPadding,showBottomSheet,navController)
        }
        else {
            PageContent(innerPadding,showBottomSheet,navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableBottomSheet(
    innerPadding: PaddingValues,
    showBottomSheet : MutableState<Boolean>,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val localDensity = LocalDensity.current

    // States for bottom sheet
    var bottomSheetHeight by remember { mutableStateOf(screenHeight) }

    // Swipe to dismiss state
    val swipeState = rememberSwipeToDismissBoxState(
        confirmValueChange = { true },
        positionalThreshold = { it * 0.5f }
    )

    SwipeToDismissBox(
        state = swipeState,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = false,
        backgroundContent = { PageContent(innerPadding , showBottomSheet , navController) },
        modifier = Modifier.fillMaxSize()
    ) {

        var offsetY by remember { mutableFloatStateOf(0f) }

        val dragState = rememberDraggableState { delta ->
            offsetY = (offsetY + delta).coerceIn(0f, with(localDensity) { (screenHeight).toPx() })
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(bottomSheetHeight)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White.copy(alpha = 0f))
                .offset(y = with(localDensity) { offsetY.toDp() })
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier.draggable(
                    orientation = Orientation.Vertical,
                    state = dragState,
                    onDragStopped = {
                        if (offsetY > with(localDensity) { screenHeight.toPx() * 0.5f }) {
                            showBottomSheet.value = false
                        } else {
                            offsetY = 0f
                        }
                    }
                )
            ) {
                Spacer(modifier = Modifier.padding(top = 250.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(5.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(2.5.dp))
                            .draggable(
                                orientation = Orientation.Vertical,
                                state = dragState,
                                onDragStopped = {
                                    if (offsetY > with(localDensity) { screenHeight.toPx() * 0.5f }) {
                                        showBottomSheet.value = false
                                    } else {
                                        offsetY = 0f
                                    }
                                }
                            )
                    )
                }

                CommentsPage(innerPadding = innerPadding)
            }
        }
    }
}

@Composable
fun CommentsPage(innerPadding: PaddingValues) {
    val viewModel: AlumniUploadViewModel = viewModel()

    val context = LocalContext.current
    val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pfp)

    val comments = remember {
        mutableStateListOf<Comment>().apply {
            addAll(clickedPost.value.comments)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(Color(163, 127, 219))
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Comments",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 5.dp),
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.padding(top = 25.dp))
            LazyColumn() {
                items(comments.size) { i ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(horizontal = 10.dp),
                        colors = CardDefaults.cardColors(Color(163, 127, 219))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                Image(
                                    modifier = Modifier
                                        .scale(3f)
                                        .padding(top = 5.dp),
                                    painter = painterResource(id = R.drawable.pfp),
                                    contentDescription = "Profile Picture"
                                )
                                Spacer(modifier = Modifier.padding(start = 30.dp))
                                Text(
                                    text = comments[i].username,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.padding(top = 30.dp))
                            Text(
                                text = comments[i].comment,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Divider(
                        color = Color.Black,
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                }
            }

            var showDialog by remember { mutableStateOf(false) } // State to control dialog visibility
            var commentText by remember { mutableStateOf("") }
            Button(
                onClick = {
                    showDialog = !showDialog
                },
                modifier = Modifier
                    .padding(start = 150.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff341539))
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp
                )
            }

            if (showDialog) {
                Dialog(onDismissRequest = {
                    showDialog = false
                }) { // Dismiss dialog on background click
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = "Add a Comment",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // TextField to enter the comment
                            TextField(
                                value = commentText,
                                onValueChange = { commentText = it },
                                placeholder = { Text("Write your comment here...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextButton(onClick = { showDialog = false }) { // Cancel button
                                    Text("Cancel", color = Color.Gray)
                                }
                                TextButton(onClick = {
                                    println(clickedPost.value.id)
                                    viewModel.addComment(
                                        comment = Comment(
                                            username = theUser.username,
                                            comment = commentText,
                                            id = clickedPost.value.id,
                                            time = ""
                                        )
                                    )
                                    comments.add(Comment(username = theUser.username, comment = commentText))
                                    commentText = ""
                                    showDialog = false
                                }) { // Submit button
                                    Text("Submit", color = Color(0xff341539))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun DisplayPostImage(fileUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(fileUrl) // Pass the Cloudinary URL here
            .crossfade(true) // Optional: Adds a crossfade animation
            .build(),
        contentDescription = "Post Image",
        modifier = Modifier
            .fillMaxSize(), // Adjust scale as needed
        contentScale = ContentScale.Crop // Adjust content scale as needed
    )
}
