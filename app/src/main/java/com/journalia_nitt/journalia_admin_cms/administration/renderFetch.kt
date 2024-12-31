package com.journalia_nitt.journalia_admin_cms

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter

@Composable
fun LoadImageWithCoil(navController: NavController,imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
    Button(onClick = { navController.popBackStack() }) {
        Text("Back")
    }
}

@Composable
fun imageHelper(navController: NavController){
    LoadImageWithCoil(navController,"https://res.cloudinary.com/dptzngjby/image/upload/v1733760451/pexels-anjana-c-169994-674010.jpg.jpg")
}