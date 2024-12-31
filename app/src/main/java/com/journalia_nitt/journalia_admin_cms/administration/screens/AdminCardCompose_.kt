package com.journalia_nitt.journalia_admin_cms.administration.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.infoPasser
import com.journalia_nitt.journalia_admin_cms.administration.response.Deadline
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_Admin_cms

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
            .size(height = 140.dp, width = 378.dp)
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
                navController.navigate(Screens_in_Admin_cms.InfoPage.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA37FDB)) // #A37FDB
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), // Ensures proper alignment and space allocation
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween // Spaces items evenly
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color.Black
                )
                Text(
                    text = description,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = author,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Deadline: $deadline",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color.White
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}