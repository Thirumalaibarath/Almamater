package com.journalia_nitt.journalia_admin_cms.administration.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.administration.infoPasser
import com.journalia_nitt.journalia_admin_cms.administration.response.Deadline
import com.journalia_nitt.journalia_admin_cms.administration.viewModels.AdminDetailsViewModel
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AdminDashBoard(navController: NavController){
    val adminDetailsViewModel: AdminDetailsViewModel = viewModel()

    LaunchedEffect(Unit) {
        adminDetailsViewModel.fetchAdminDetails()
    }

    val detailsList = adminDetailsViewModel.detailsList

    Column(
        modifier= Modifier
        .fillMaxSize()
        .padding(top = 10.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        var mode by remember{ mutableIntStateOf(0) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    mode = 0
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(mode==0) Color(0xFFA37FDB) else Color(0xFFCDC1FF),
                ),
                border = ButtonDefaults.outlinedButtonBorder,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .shadow(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    )
            ) {
                Text(
                    text = "DEADLINE",
                    fontSize = 15.sp,
                    fontFamily = urbanist,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
            Button(
                onClick = {
                    mode = 1
                },
                border = ButtonDefaults.outlinedButtonBorder,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(mode==1) Color(0xFFA37FDB) else Color(0xFFCDC1FF),
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .shadow(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    )
            ) {
                Text(
                    text = "ANNOUNCEMENT",
                    fontSize = 15.sp,
                    fontFamily = urbanist,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            detailsList.forEach { user ->
                items(user.details) { announcement ->
                    AdminCard(
                        navController = navController,
                        title = announcement.title,
                        description = announcement.description,
                        author = announcement.author,
                        deadline = announcement.deadline,
                        pdfUrl = announcement.file_url.toString(),
                        link1 = announcement.link1,
                        link2 = announcement.link2
                    )
                }
            }
        }
    }
}

@Composable
fun AdminCard(
    navController: NavController,
    title: String = "",
    description: String = "",
    author: String = "",
    deadline: String = "",
    pdfUrl: String = "",
    link1: String = "",
    link2: String = ""
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(shape = RoundedCornerShape(10.dp), elevation = 5.dp)
            .clickable {
                infoPasser.value = Deadline(
                    author = author,
                    deadline = deadline,
                    description = description,
                    file_url = pdfUrl,
                    link1 = link1,
                    link2 = link2,
                    mode = 1,
                    title = title
                )
                navController.navigate(Screens.AdminViewPostScreen.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA37FDB))
    ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column()
                {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = urbanist,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 10.dp,top = 10.dp).fillMaxWidth(0.9f),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = description,
                        fontSize = 18.sp,
                        fontFamily = urbanist,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp).fillMaxWidth(0.9f),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = author,
                        fontSize = 15.sp,
                        fontFamily = urbanist,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp).fillMaxWidth(0.9f),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                HorizontalDivider(color = Color.White,modifier = Modifier.fillMaxWidth(0.85f),
                    thickness = 1.5.dp)
                Text(
                    text = "Deadline:   $deadline",
                    fontSize = 18.sp,
                    fontFamily = urbanist,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp).fillMaxWidth(0.9f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

    }
}