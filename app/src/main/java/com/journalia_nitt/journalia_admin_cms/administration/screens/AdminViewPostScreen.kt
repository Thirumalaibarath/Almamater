package com.journalia_nitt.journalia_admin_cms.administration.screens

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.infoPasser
import com.journalia_nitt.journalia_admin_cms.administration.response.Deadline
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist
import java.time.LocalDate

fun getMonthInt(month : String) : Int {
    val ans = when(month) {
        "Jan" -> 1
        "Feb" -> 2
        "Mar" -> 3
        "Apr" -> 4
        "May" -> 5
        "Jun" -> 6
        "Jul" -> 7
        "Aug" -> 8
        "Sep" -> 9
        "Oct" -> 10
        "Nov" -> 11
        "Dec" -> 12
        else -> 0
    }
    return ans
}

fun getMonth(month : Int) : String {
    val ans =  when(month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid Month"
    }
    return ans.uppercase()
}

fun getMonthCalender(
    year : Int ,
    month : Int
) : List<List<Int>> {

    val specificDate = LocalDate.of(year,month,1)

    val firstDayOfMonth = specificDate.dayOfWeek.value
    val noOfDaysInTheMonth = specificDate.lengthOfMonth()

    val dateInRow = mutableListOf<List<Int>>()
    var dateInCurrentRow = mutableListOf<Int>()

    var n = firstDayOfMonth

    for(i in 1..noOfDaysInTheMonth) {
        n += 1
        dateInCurrentRow.add(i)
        if(n%7==0) {
            while(dateInCurrentRow.size<7) dateInCurrentRow.add(0,0)
            dateInRow.add(dateInCurrentRow.toList())
            dateInCurrentRow = mutableListOf()
        }
    }
    if(dateInCurrentRow.isNotEmpty()) {
        while(dateInCurrentRow.size<7) dateInCurrentRow.add(0)
        dateInRow.add(dateInCurrentRow)
    }
    return dateInRow.toList()
}

@Composable
fun AdminViewPostScreen(
    item : Deadline? = Deadline(
        "",
        "",
        "",
        "",
        "",
        "",
        1,
        ""
    ),
    navController: NavController,
) {
    var item = infoPasser.value

    val verticalScroll = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(verticalScroll),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = item.title.toString(),
            fontFamily = urbanist,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        val month = getMonth(item.deadline.substring(3,5).toInt())
        Text(
            text = item.deadline.substring(0,2) + " " + month.lowercase() + " " + item.deadline.substring(6,10),
            fontFamily = urbanist,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.author,
            fontFamily = urbanist,
            fontSize = 16.sp
        )
        Card(
            colors = CardDefaults.cardColors(Color(163, 127, 219))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Edit ",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = urbanist
                )
                Icon(
                    painter = painterResource(id = R.drawable.edit_icon),
                    contentDescription = "Edit button",
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )
            }
        }
        OutlinedTextField(
            value = item.description.toString(),
            enabled = false,
            onValueChange = {
                //do nothing
            },
            textStyle = TextStyle(
                fontFamily = urbanist,
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(300.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Text(
            text = "IMPORTANT LINKS",
            fontFamily = urbanist,
            fontSize = 20.sp
        )

        if(item.link1.toString().isEmpty() && item.link2.toString().isEmpty()) {
            Text(
                text = "No Important links found",
                fontFamily = urbanist,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        else if(item.link1.toString().isNotEmpty()) {
            LinkCard(item,navController)
        }
        else if(item.link2.toString().isNotEmpty()) {
            LinkCard(item,navController)
        }
        Text(
            text = "CIRCULAR",
            fontFamily = urbanist,
            fontSize = 20.sp
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(horizontal = 20.dp)
                .clickable {

                },
            colors = CardDefaults.cardColors(
                containerColor =  Color(163, 127, 219)
            )
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Text(
                    text = "Click to view circular",
                    fontFamily = urbanist,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
                )
            }
        }
    }
}


@Composable
fun LinkCard(item: Deadline,navController: NavController) {
    val clipboardManager = LocalClipboardManager.current
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(horizontal = 20.dp)
            .clickable {
                navController.navigate("WebViewScreen/${Uri.encode(item.link1.toString())}")
            },
        colors = CardDefaults.cardColors(
            containerColor =  Color(163, 127, 219)
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.link1.toString(),
                fontFamily = urbanist,
                color = Color.White,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(0.7f).padding(horizontal = 5.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.copy_link_icon),
                contentDescription = "copy-link-icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        clipboardManager.setText( AnnotatedString(item.link1.toString()))
                    }
                ,
                tint = Color.White
            )
        }
    }
}
