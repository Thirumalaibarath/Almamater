package com.journalia_nitt.journalia_admin_cms.student.navigationDeck

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.clearUserDetails
import com.journalia_nitt.journalia_admin_cms.ui.theme.color_3
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun Page(
    currentPage : @Composable () -> Unit,
    navController: NavController,
    searchBar : Boolean,
    heading : String,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()).fillMaxWidth(0.6f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "alma",
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(R.font.urbanist)),
                        fontSize = 25.sp,
                        color = Color(150,103,224)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text="mater",
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(R.font.urbanist)),
                        fontSize = 25.sp,
                        color = Color(188, 128, 240),
                        lineHeight = 30.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider( thickness = 1.5.dp,color = Color.DarkGray)
                NavigationDrawerItem(
                    label = "Webmail Login",
                    image = R.raw.web_mail,
                    navController = navController,
                    route = Screens.WebMailScreen.route,
                )
                NavigationDrawerItem(
                    label = "Notification Inbox",
                    image = R.raw.notification_inbox,
                    navController = navController,
                    route = Screens.StudentHomeScreen.route
                )
                HorizontalDivider( thickness = 1.5.dp,color = Color.DarkGray)
                NavigationDrawerItem(
                    label = "Explore Clubs",
                    image = R.raw.explore_clubs_icon,
                    navController = navController,
                    route = Screens.StudentClubDirectoryScreen.route
                )
                NavigationDrawerItem(
                    label = "Explore Fests",
                    image = R.raw.fest_icon,
                    navController = navController,
                    route = Screens.StudentFestDirectoryScreen.route
                )
                HorizontalDivider( thickness = 1.5.dp,color = Color.DarkGray)
                NavigationDrawerItem(
                    label = "Admin Board",
                    image = R.raw.admin_board_icon,
                    navController = navController,
                    route = Screens.StudentAdminDashboardScreen.route
                )
                HorizontalDivider( thickness = 1.5.dp,color = Color.DarkGray)
                Spacer(modifier = Modifier.weight(1f))
                NavigationDrawerItem(
                    label = "Sign Out",
                    image = R.raw.log_out,
                    navController = navController,
                    route = Screens.UserRoleSelectionScreen.route,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = color_3)
                .padding(WindowInsets.systemBars.asPaddingValues()),
            topBar = {
                Column(
                    modifier = Modifier
                )
                {
                    Header(heading,scope,drawerState)
                    if (searchBar) {
                        Spacer(modifier = Modifier.height(10.dp))
                        SearchBar()
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            },

            content = {paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    currentPage()
                }
            }
        )
    }
}

@Composable
fun NavigationDrawerItem(
    label:String,
    image:Any,
    navController: NavController,
    route:String,
    modifier : Modifier = Modifier
)
{
    val context = LocalContext.current
    NavigationDrawerItem(
        modifier = modifier,
        label = { Text(text = label ,fontSize = 18.sp, fontFamily = urbanist) },
        selected = false,
        icon = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "nitt_fest_logo",
                modifier = Modifier.size(25.dp),
                colorFilter = ColorFilter.tint(Color.Black)
            )
        },
        onClick = {
            if (label == "Sign Out") {
                clearUserDetails(context = context)
                navController.navigate(route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
                else
                {
                    navController.navigate(route)
                }

            }
    )
}
