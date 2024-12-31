package com.journalia_nitt.journalia_admin_cms.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Page(
    innerPadding: PaddingValues,
    currentPage : @Composable () -> Unit,
    navController: NavController,
    searchBar : Boolean,
    heading : String
) {
    val showSideBar = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(innerPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TopBar(showSideBar,heading, navController)
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Divider(
            modifier = Modifier,
            thickness = 1.5.dp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            if (searchBar) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    SearchBar()
                }
            }
            currentPage()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            BottomBar(navController = navController)
        }
    }
    if(showSideBar.value) {
        SideBar(showSideBar,navController)
    }
}