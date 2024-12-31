package com.journalia_nitt.journalia_admin_cms.navigation

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.journalia.Student.SharedPreferences.clearUserDetails
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun TopBar(
    showSideBar : MutableState<Boolean>,
    heading : String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    Log.d("NAVIGATION CHECK", "BACK BUTTON CLICKED")
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "User",
                        tint = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp)
                            .scale(1.1f)
//                            .clickable{
//                                Log.d("NAVIGATION CHECK","BACK BUTTON CLICKED")
//                                navController.popBackStack()
//                            }
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = heading,
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 5.dp, start = 10.dp),
                        fontFamily = urbanist,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                IconButton(
                    onClick = {
                        showSideBar.value = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Sidebar",
                        tint = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 10.dp)
                            .scale(1.2f)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Divider(
                modifier = Modifier,
                thickness = 3.dp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.Bottom
    ) {
        Divider(
            modifier = Modifier,
            thickness = 1.dp,
            color = Color.LightGray
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.padding(start = 1.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            navController.navigate(Screens.HomePage.route)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "home page",
                        tint = Color.Black,
                        modifier = Modifier
                    )
                    Text(
                        text = "Home",
                        fontSize = 12.sp,
                        fontFamily = urbanist
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            navController.navigate(Screens.CalenderPage.route)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "calender page",
                        tint = Color.Black,
                        modifier = Modifier
                    )
                    Text(
                        text = "Calendar",
                        fontSize = 12.sp,
                        fontFamily = urbanist
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screens.PostCreationPage.route)
                    },
                    modifier = Modifier.scale(1.3f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = "Add circle",
                        tint = Color(163,127,219),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            navController.navigate(Screens.BookMarkPage.route)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_bookmark_border_24),
                        contentDescription = "Bookmark?",
                        tint = Color.Black,
                        modifier = Modifier
                    )
                    Text(
                        text = "Bookmarks",
                        fontSize = 12.sp,
                        fontFamily = urbanist
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            navController.navigate(Screens.ProfilePage.route)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "My account page",
                        tint = Color.Black,
                        modifier = Modifier
                    )
                    Text(
                        text = "Account",
                        fontSize = 12.sp,
                        fontFamily = urbanist
                    )
                }
                Spacer(modifier = Modifier.padding(end = 5.dp))
            }
        }
    }
}

@Composable
fun SearchBar() {
    var textFieldValue by remember { mutableStateOf("") }
//    val filteredPosts = remember(query, posts) {
//        if (query.isBlank()) posts
//        else posts.filter {
//            it.title.contains(query, ignoreCase = true) ||
//                    it.description.contains(query, ignoreCase = true)
//        }
//    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Search for posts, deadlines and info",
                fontSize = 14.sp,
                fontFamily = urbanist
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search icon",
            )
        }
    )
}

@Composable
fun SideBar(
    showSideBar : MutableState<Boolean>,
    navController: NavController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Card(
            modifier = Modifier
                .width(220.dp)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 10.dp, top = 10.dp),
//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier.clickable{
                                showSideBar.value = false
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "close menu"
                        )
                        Text(
                            text = "alma mater",
                            fontSize = 29.sp,
                            fontFamily = urbanist,
                            color = Color(150, 103, 224)
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        thickness = 3.dp,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp,0.dp,0.dp,0.dp)
                            .clickable {
                                navController.navigate(Screens.Webmail.route)
                            },
                        horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.nittlogo),
                            contentDescription = null
                        )
                        Text(
                            text = "Webmail Login",
                            fontSize = 18.sp,
                            fontFamily = urbanist
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp,0.dp,0.dp,0.dp)
                            .clickable {

                            },
                        horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.notifications),
                            contentDescription = null
                        )
                        Column()
                        {
                            Text(
                                text = "Notification",
                                fontSize = 18.sp,
                                fontFamily = urbanist
                            )
                            Text(
                                text = "Inbox",
                                fontSize = 18.sp,
                                fontFamily = urbanist
                            )
                        }

                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        thickness = 3.dp,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp,0.dp,0.dp,0.dp)
                            .clickable {
                                navController.navigate(Screens.ClubDirectory.route)
                            },
                        horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.clubs),
                            contentDescription = null
                        )
                        Text(
                            text = "Explore Clubs",
                            fontSize = 18.sp,
                            fontFamily = urbanist
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp,0.dp,0.dp,0.dp)
                            .clickable {
                                navController.navigate(Screens.FestDirectory.route)
                            },
                        horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.fests),
                            contentDescription = null
                        )
                        Text(
                            text = "Explore Fests",
                            fontSize = 18.sp,
                            fontFamily = urbanist
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        thickness = 3.dp,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp,0.dp,0.dp,0.dp)
                            .clickable {
                                navController.navigate(Screens.AdminPage.route)
                            },
                        horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.adminboard),
                            contentDescription = null
                        )
                        Text(
                            text = "Admin Board",
                            fontSize = 18.sp,
                            fontFamily = urbanist
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp)
                            .clickable {
                                clearUserDetails(context = context)
                                navController.navigate(Screens.LoginPage.route){
                                    popUpTo(0){
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            },
                        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sign Out",
                            fontSize = 18.sp,
                            fontFamily = urbanist
                        )
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.signout),
                            contentDescription = null
                        )
                    }

            }
        }
    }
}