package com.journalia_nitt.journalia_admin_cms.administration.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.viewModels.AdminDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashBoard(token:String ,innerPaddingValues: PaddingValues , navController: NavController){
    val scrollState = rememberScrollState()
    val adminDetailsViewModel: AdminDetailsViewModel = viewModel()

    // Fetch admin details when the composable is first launched
    LaunchedEffect(Unit) {
        adminDetailsViewModel.fetchAdminDetails()
    }

    // Observe the list of admin details from the ViewModel
    val detailsList = adminDetailsViewModel.detailsList

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)){
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier= Modifier
            .fillMaxWidth()
            .height(50.dp),
            contentAlignment = Alignment.Center){
            Text(
                modifier = Modifier
                    .offset(x=(-10).dp),
                text="ADMIN DASHBOARD",
                fontSize = 24.sp    ,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
            Image(
                painter = rememberVectorPainter(Icons.Default.Menu),
                contentDescription = "menu",
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 150.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Adjusted height for better placeholder visibility
            contentAlignment = Alignment.Center
        ) {
            var textFieldValue by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(375.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(12.dp))
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp)),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center // Center placeholder horizontally and vertically
                    ) {
                        Text(
                            text = "Search for deadlines",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            color = Color.Gray
                        )
                    }
                },
                trailingIcon = { // Place the search icon at the end
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon",
                        tint = Color.Gray
                    )
                },
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) // Padding for outer margins
                .height(60.dp), // Adjusted to match button height
            horizontalArrangement = Arrangement.SpaceEvenly // Ensures even spacing
        ) {
            Button(
                onClick = { /* TODO: Handle Deadline Click */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA37FDB), // Highlighted background color
                    contentColor = Color.White // Text color for contrast
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(50.dp)// Space between buttons
                    .border(
                        shape = RoundedCornerShape(10.dp),
                        width = 1.dp,
                        color = Color.Black
                    )
                    .shadow(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    )
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp, end =10.dp),
                    text = "DEADLINE",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black // Matches contentColor
                )
            }
            Spacer(modifier = Modifier.width(26.dp)) // Space between buttons
            Button(
                onClick = { /* TODO: Handle Announcement Click */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffcdc1ff)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier // Ensures equal size for both buttons
                    .height(50.dp)
                    .border(
                        shape = RoundedCornerShape(10.dp),
                        width = 1.dp,
                        color = Color.Black
                    )
                    .shadow(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    )
            ) {
                Text(
                    text = "ANNOUNCEMENT",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }


        Spacer(modifier = Modifier.height(30.dp))

        Box(modifier=Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            LazyColumn(
                modifier = Modifier.height(800.dp),
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
                            link1 = announcement.link1.toString(),
                            link2 = announcement.link2.toString()
                        )
                    }
                }
            }

        }
    }
}

