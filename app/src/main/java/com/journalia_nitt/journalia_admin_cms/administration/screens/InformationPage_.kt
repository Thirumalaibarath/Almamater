package com.journalia_nitt.journalia_admin_cms.administration.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate
import androidx.core.content.ContextCompat.startActivity
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.infoPasser
import com.journalia_nitt.journalia_admin_cms.administration.response.Deadline
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

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
fun AdminDetailsPage(
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
    innerPaddingValues: PaddingValues
) {
    var item = infoPasser.value

    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )
    val context= LocalContext.current

    val verticalScroll = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(verticalScroll).padding(innerPaddingValues)
    ) {
        Row (
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(Modifier.width(110.dp))
            Text(
                text = "CIRCULAR",
                fontFamily = urbanist,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Divider(
            color = Color.LightGray,
            thickness = (1).dp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(start = 60.dp))
            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = item.title.toString(),
                fontFamily = urbanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(start = 80.dp))
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        val month = getMonth(item.deadline.substring(3,5).toInt())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(start = 50.dp))
            Text(
                text = item.deadline.substring(0,2) + " " + month.lowercase() + " " + item.deadline.substring(6,10),
                fontFamily = urbanist,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(start = 50.dp))
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.author,
                fontFamily = urbanist,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Card(
            modifier = Modifier
                .size(95.dp, 45.dp)
                .align(Alignment.CenterHorizontally),
            colors = CardDefaults.cardColors(Color(163, 127, 219))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(start = 10.dp))

                Text(
                    text = "Edit ",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = urbanist
                )
                IconButton(
                    onClick = {
                    },
                    modifier = Modifier.scale(2f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.edit_button),
                        contentDescription = "Edit button"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
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
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "IMPORTANT LINKS",
                fontFamily = urbanist,
                fontSize = 20.sp
            )
        }
        if(item.link1.toString().isEmpty() && item.link2.toString().isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "No Important links found",
                    fontFamily = urbanist,
                    fontSize = 16.sp
                )
            }
        }
        else if(item.link1.toString().isNotEmpty()) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            LinkCard(item)
        }
        else if(item.link2.toString().isNotEmpty()) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(50.dp)
                    .clickable {

                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 30.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 30.dp),
                                    text = item.link2.toString(),
                                    fontFamily = urbanist,
                                    color = Color.White
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.link),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 30.dp)
                                    .scale(2f)
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CIRCULAR",
                fontFamily = urbanist,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
                .height(50.dp)
                .clickable {

                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Click to view circular",
                        fontFamily = urbanist,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
    }
}


@Composable
fun LinkCard(item: Deadline) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )
    val context = LocalContext.current // Obtain the context here
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .height(50.dp)
            .clickable {
                val url = item.link1.toString()
                val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url))
                startActivity(context, intent, null)
            }
                ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.link1.toString(),
                        fontFamily = urbanist,
                        color = Color.White
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.link),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .scale(2f)
                )
            }
        }
    }
}
