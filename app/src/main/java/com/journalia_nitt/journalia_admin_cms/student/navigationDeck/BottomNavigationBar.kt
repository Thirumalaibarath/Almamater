package com.journalia_nitt.journalia_admin_cms.student.navigationDeck

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.responses.Navigation
import com.journalia_nitt.journalia_admin_cms.ui.theme.color_3
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun BottomNavigationBar(navController: NavController)
{
    val componentList = listOf(
        Navigation(Screens.StudentHomeScreen.route,Icons.Outlined.Home,"Home",25,Color.Black,true),
        Navigation(Screens.StudentCalendarScreen.route,Icons.Outlined.DateRange,"Calendar",25,Color.Black,true),
        Navigation(Screens.StudentCreateAPostScreen.route,Icons.Outlined.AddCircle,"",30, color_3,false),
        Navigation(Screens.StudentBookMarkScreen.route,ImageVector.vectorResource(R.drawable.baseline_bookmark_border_24),"Bookmarks",25,Color.Black,true),
        Navigation(Screens.StudentProfileScreen.route,Icons.Outlined.Person,"Account",25,Color.Black,true),
    )
    Column()
    {
        Divider(
            modifier = Modifier,
            thickness = 1.dp,
            color = Color.LightGray
        )
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.White).padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for(i in componentList)
            {
                BottomBarComponent(navController = navController, route = i.route,icon =i.icon,text =i.text, size = i.size,tint = i.tint,isText = i.isText)
            }
        }
    }
}
@Composable
fun BottomBarComponent(navController: NavController,route: String,icon:ImageVector,text:String,size : Int,tint:Color,isText:Boolean)
{
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .clickable {
                navController.navigate(route)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "My account page",
            tint = tint,
            modifier = Modifier.size(size.dp)
        )
        if(isText)
        {
            Text(
                text = text,
                fontSize = 12.sp,
                fontFamily = urbanist
            )
        }
    }
}