package com.journalia_nitt.journalia_admin_cms.student.navigationDeck

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun SearchBar()
{
    var textFieldValue by remember { mutableStateOf("") }
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