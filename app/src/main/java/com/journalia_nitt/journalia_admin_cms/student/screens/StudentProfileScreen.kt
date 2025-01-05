package com.journalia_nitt.journalia_admin_cms.student.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.student.ContentResolver1
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.getUserDetails
import com.journalia_nitt.journalia_admin_cms.student.viewModels.FetchViewModel
import com.journalia_nitt.journalia_admin_cms.student.viewModels.UploadViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun StudentProfileScreen(
    navController: NavController
)
{
    val userFetch: FetchViewModel = viewModel()
    LaunchedEffect(Unit) {
        userFetch.fetchUser("111")
    }
    val context = LocalContext.current
    val userDetails = getUserDetails(context = context)
    val posts=userFetch.posts.value.data.details
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }
    val uploadPic: UploadViewModel = viewModel()
    Column() {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.padding(start = 40.dp)
            ) {
                IconButton(
                    onClick = {
                        imagePickerLauncher.launch("image/*")
                        Log.d("Image",selectedImageUri.toString())
                        if (selectedImageUri != null) {
                            uploadPic.uploadFile(selectedImageUri, ContentResolver1.value)
                        }


                    },
                    modifier = Modifier.scale(4f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "Profile",
                        contentScale = ContentScale.FillBounds
                    )
                }
                Card(
                    modifier = Modifier.padding(start = 60.dp , top = 60.dp),
                    colors = CardDefaults.cardColors(Color.Gray),
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Edit",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (userDetails != null) {
                Text(
                    text = userDetails.name,
                    fontFamily = urbanist
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (userDetails != null) {
                Text(
                    text = userDetails.collegeId,
                    fontFamily = urbanist
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .height(70.dp)
                    .width(150.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Number of Posts",
                        fontSize = 14.sp,
                        fontFamily = urbanist
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = "1400",
                        fontSize = 14.sp,
                        fontFamily = urbanist
                    )
                }
            }
            Card(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .height(70.dp)
                    .width(150.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Upvotes",
                        fontSize = 14.sp,
                        fontFamily = urbanist
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = "1400",
                        fontSize = 14.sp,
                        fontFamily = urbanist
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "POSTS",
                fontFamily = urbanist,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 1.dp))
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
                            if (post.fileUrl=="") {
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
