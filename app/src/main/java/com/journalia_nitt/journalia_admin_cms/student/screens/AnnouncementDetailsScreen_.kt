package com.journalia_nitt.journalia_admin_cms.student.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.student.responses.BookMark
import com.journalia_nitt.journalia_admin_cms.student.responses.Deadline
import com.journalia_nitt.journalia_admin_cms.student.viewModels.bookMarkViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AdminDetailsPage(
    item : Deadline?,
    navController : NavController
) {
    if(item == null) return
    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )
    val bookMark1= bookMarkViewModel()
    val bookMarkDetail= convertDeadlineToBookMark(item,"111")
    val context= LocalContext.current

    val verticalScroll = rememberScrollState()

    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.verticalScroll(verticalScroll)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(start = 60.dp))
            Text(
                text = item.title.toString(),
                fontFamily = urbanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(start = 80.dp))
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        val month = getMonth(item.deadline.substring(3,5).toInt())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(start = 50.dp))
            Text(
                text = item.deadline.substring(0,2) + " " + month.lowercase() + " " + item.deadline.substring(6,10),
                fontFamily = urbanist,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(start = 50.dp))
            val bookmarked = remember { mutableStateOf(false) }
            if(!bookmarked.value) {
                Image(
                    painter = painterResource(id = R.drawable.bookmark_not),
                    contentDescription = null,
                    modifier = Modifier
                        .scale(3f)
                        .padding(top = 5.dp)
                        .clickable {
                            bookmarked.value = true
                            bookMark1.postBookMark(
                                bookMarkDetail
                            )
                            Toast
                                .makeText(context, "Bookmarked", Toast.LENGTH_SHORT)
                                .show()
                        }
                )
            }
            else {
                Image(
                    painter = painterResource(id = R.drawable.bookmark_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .scale(3f)
                        .padding(top = 5.dp)
                        .clickable {
                            bookmarked.value = true
                            bookMark1.postBookMark(
                                bookMarkDetail
                            )
                            Toast
                                .makeText(context, "Bookmarked", Toast.LENGTH_SHORT)
                                .show()
                        }
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.author,
                fontFamily = urbanist,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        OutlinedTextField(
            value = item.description.toString(),
            enabled = false,
            onValueChange = {
                //do nothing
            },
            textStyle = TextStyle(
                fontFamily = urbanist,
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(300.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "IMPORTANT LINKS",
                fontFamily = urbanist,
                fontSize = 20.sp
            )
        }
        if(item.link1.toString().isEmpty() && item.link2.toString().isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "No Important links found",
                    fontFamily = urbanist,
                    fontSize = 16.sp
                )
            }
        }
        else if(item.link1.toString().isNotEmpty()) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(50.dp)
                    .clickable {
                        val url = item.link1
                        if (url.isNullOrEmpty() || !Patterns.WEB_URL.matcher(url).matches()) {
                            Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
                        } else {
                            val fixedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                "https://$url"
                            } else {
                                url
                            }
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fixedUrl))
                            val resolvedActivities = context.packageManager.queryIntentActivities(intent, 0)
                            if (resolvedActivities.isNotEmpty()) {
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "No app found to handle this link", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                ) {
                    Row(
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 30.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = item.link1.toString(),
                                fontFamily = urbanist,
                                color = Color.White
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 30.dp)
                                .scale(2f)
                        )
                    }
                }
            }
        }
        else if(item.link2.toString().isNotEmpty()) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(50.dp)
                    .clickable {
                        val url = item.link2
                        if (url.isNullOrEmpty() || !Patterns.WEB_URL.matcher(url).matches()) {
                            Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
                        } else {
                            val fixedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                "https://$url"
                            } else {
                                url
                            }
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fixedUrl))
                            val resolvedActivities = context.packageManager.queryIntentActivities(intent, 0)
                            if (resolvedActivities.isNotEmpty()) {
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "No app found to handle this link", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 30.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 30.dp),
                                    text = item.link2.toString(),
                                    fontFamily = urbanist,
                                    color = Color.White
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.link),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 30.dp)
                                    .scale(2f)
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CIRCULAR",
                fontFamily = urbanist,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
                .height(50.dp)
                .clickable {
                    Log.d("url",item.file_url.toString())
                    if(item.file_url.toString().endsWith(".jpg")) {
                        showDialog.value = true
                    }
                    else if(item.file_url.toString().endsWith(".pdf")) {
                        openPdf(context,item.file_url.toString(), navController)
                    }
                    else {
                        Toast.makeText(context, "No file found", Toast.LENGTH_SHORT).show()
                        return@clickable
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Click to view circular",
                        fontFamily = urbanist,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
    }
    if(showDialog.value) {
        ShowImageInDialog(showDialog,item.file_url)
    }
}

fun convertDeadlineToBookMark(deadline: Deadline, token:String): BookMark {
    return BookMark(
        token = token,
        author = deadline.author,
        deadline = deadline.deadline,
        description = deadline.description,
        file_url = deadline.file_url,
        link1 = deadline.link1,
        link2 = deadline.link2,
        mode = deadline.mode,
        title = deadline.title
    )
}