package com.journalia_nitt.journalia_admin_cms.student.navigationDeck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Header(
    heading : String,
    scope: CoroutineScope,
    drawerState: DrawerState,
)
{
    Column(
    )
    {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ))
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        scope.launch { drawerState.open() }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Sidebar",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                }
                TextForHeader(
                    heading = heading,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            if(heading != "HOME")
            {
                Divider(
                    modifier = Modifier,
                    thickness = 1.5.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}
@Composable
fun TextForHeader(
    heading : String,
    modifier: Modifier
)
{
    if(heading != "HOME")
    {
        Text(
            text = heading,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = urbanist,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
    else
    {
        Column(
            modifier = Modifier.padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "alma",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 36.sp,
                    color = Color(150,103,224)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text="mater",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 36.sp,
                    color = Color(188, 128, 240),
                    lineHeight = 30.sp
                )
            }
            Text(
                text="made for NIT Trichy",
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontSize = 14.sp,
                color = Color(150,103,224)
            )
        }
    }
}

