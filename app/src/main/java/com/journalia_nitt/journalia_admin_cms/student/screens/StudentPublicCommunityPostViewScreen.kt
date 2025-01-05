package com.journalia_nitt.journalia_admin_cms.student.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.clickedPost
import com.journalia_nitt.journalia_admin_cms.alumni.screens.DisplayPostImage
import com.journalia_nitt.journalia_admin_cms.student.responses.UserFetchClass
import com.journalia_nitt.journalia_admin_cms.student.responses.userComments
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.getUserDetails
import com.journalia_nitt.journalia_admin_cms.student.viewModels.handleUserUpvotes
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun StudentPublicCommunityPostViewScreen(navController: NavController, post: UserFetchClass) {
    var verticalScroll = rememberScrollState()
    val upvotesHandle: handleUserUpvotes = handleUserUpvotes()
    Log.d("ImageLink", post.fileUrl.toString())
    val context = LocalContext.current
    val userDetails = getUserDetails(context = context)
    val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.group)
    var comment by remember { mutableStateOf("") }
    var commentBox by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .verticalScroll(verticalScroll)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = post.userName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = urbanist
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = 18.dp),
                        text = "Student",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        fontFamily = urbanist
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = post.tag.toString(),
                            fontFamily = urbanist,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
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
                    text = post.title,
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
            if (post.fileUrl.toString().isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .width(353.dp)
                        .height(182.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    DisplayPostImage(post.fileUrl.toString())
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = post.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                fontSize = 16.sp,
                fontFamily = urbanist
            )
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
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Box {
                IconButton(
                    onClick = {
                        if (!post.comments) {
                            Toast.makeText(context, "Comments are disabled", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            commentBox = true
                        }
                    },
                    modifier = Modifier
                        .scale(2f)
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.comment),
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
            Box {
                IconButton(
                    onClick = {
                        upvotesHandle.upvotePost(post.token, post.id)
                    },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = painterResource(R.drawable.upvotes),
                        contentDescription = "Like button",
                        modifier = Modifier.size(23.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 31.dp),
                    text = post.upvotes.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = urbanist
                )
            }
        }
        if (commentBox) {
            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
            )
            TextButton(onClick = {
                commentBox = false
                upvotesHandle.addComment(
                    post.token, post.id, userComments(
                        text = comment,
                        commentedBy = userDetails?.name.toString(),
                    )
                )
                comment = ""
            }) {
                Text(text = "Post")
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        if (post.commentsPosted.isEmpty()) {
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
            post.commentsPosted.forEach { comment ->
                userCommentCard(comment, bitmap)
            }
        }
    }
}

@Composable
fun userCommentCard(comment: userComments, bitmap: Bitmap) {
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
                    text = comment.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
                Text(
                    text = comment.commentedBy,
                    fontSize = 12.sp,
                    color = Color.White,
                    lineHeight = 15.sp
                )
            }
        }
    }
}