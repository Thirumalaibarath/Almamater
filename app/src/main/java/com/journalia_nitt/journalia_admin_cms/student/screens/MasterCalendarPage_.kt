package com.journalia_nitt.journalia_admin_cms.student.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.firebase.fireStore
import com.journalia_nitt.journalia_admin_cms.firebase.reminderToCalendar
import com.journalia_nitt.journalia_admin_cms.student.viewModels.DeadlineViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CalenderPage(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val events = remember { mutableStateListOf<Pair<String, String>>() }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val fetchedEvents = fireStore(context = context) // Call the suspend function
        events.addAll(fetchedEvents)
    }
    var response by remember { mutableStateOf("") }
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf(today) }
    val deadlineViewModel: DeadlineViewModel = viewModel()
    LaunchedEffect(Unit) {
        deadlineViewModel.fetchDeadlines()
    }
    val deadlines = deadlineViewModel.deadlines.collectAsState()
    val error = deadlineViewModel.error.collectAsState()

    if(error.value != null) {
        Log.d("Error" , error.value.toString())
    }
    else {
        Log.d("Deadlines in calender page", deadlines.value.toString())
    }

    var showPopup by remember { mutableStateOf(false) }
    // custom reminder

    var date by remember{ mutableStateOf("") }
    var title by remember{ mutableStateOf("") }
    if(showPopup)
    {
        date = selectedDate.toString()
        Dialog(
            onDismissRequest = { showPopup = false },
        ) {
            val gradient = Brush.linearGradient(
                colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
            )
            Column(modifier = Modifier.fillMaxWidth().background(color = Color.White, shape = RoundedCornerShape(12.dp)),horizontalAlignment = Alignment.CenterHorizontally)
            {
                Column(modifier = Modifier.fillMaxWidth().padding(0.dp,10.dp),horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(
                        text = "Get notification of events and important",color = Color(150, 103, 224), fontWeight = FontWeight.Bold, fontFamily = urbanist
                    )
                    Text(
                        text = "dates by saving it in your calendar", color = Color(150, 103, 224), fontWeight = FontWeight.Bold, fontFamily = urbanist
                    )
                }
                Column(modifier = Modifier.fillMaxWidth().background(brush = gradient,shape = RoundedCornerShape(0.dp,0.dp,12.dp,12.dp)).fillMaxWidth(0.9f).padding(10.dp,10.dp,10.dp,0.dp),horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(text = "Title of the Reminder",modifier = Modifier.align(Alignment.Start),color=Color.White, fontSize = 18.sp, fontFamily = urbanist )
                    TextField(
                        value = title,
                        onValueChange = { title = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = urbanist,
                            fontSize = 18.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,0.dp,10.dp).border(1.5.dp,Color.Black, shape = RoundedCornerShape(12.dp)),
                    )
                    Text(text = "Reminder Date",modifier = Modifier.align(Alignment.Start) ,color=Color.White, fontSize = 18.sp, fontFamily = urbanist )
                    TextField(
                        value = date,
                        onValueChange = { date = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = urbanist,
                            fontSize = 18.sp
                        ),
                        label = {
                            Text(
                                text = "YYYY-MM-DD",color=Color.Black, fontFamily = urbanist
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,0.dp,10.dp).border(1.5.dp,Color.Black, shape = RoundedCornerShape(12.dp)),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.calendar_icon),
                                contentDescription = "calendar_icon",
                                modifier = Modifier.clickable {
                                }
                            )
                        }
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                response = reminderToCalendar(title = title,date = date,context = context).toString()
                                Toast.makeText(context, response, Toast.LENGTH_LONG).show()
                                title=""
                                date=""
                            }
                            showPopup = false
                        },
                        colors = ButtonColors(
                            containerColor = Color.Black,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                    ) {
                        Text(text = "Submit", fontSize = 18.sp,color = Color.White,fontFamily = urbanist)
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        selectedDate = calendarScreen()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .size(130.dp, 40.dp)
                    .clickable {
                        showPopup = true
                    },
                colors = CardDefaults.cardColors(Color(157, 98, 253))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.padding(start = 15.dp))
                    Text(
                        text = "Custom",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = urbanist
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Plus",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(events) { item->
                if(item.first == selectedDate.toString())
                {
                    EachDeadline(item)
                }
            }
        }
    }
}
@Composable
fun EachDeadline(
    event : Pair<String, String>
) {

    var deadline by remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(60.dp)
            .clickable {
//                    deadline=item.deadline.format(formatter).toString()
            },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Log.d("Deadline",deadline)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = event.second,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = urbanist,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxHeight(0.5f)
                )
                Spacer(modifier = Modifier.padding(top = 2.dp))
                Row() {
                    Text(
                        text = "Deadline: ",
                        color = Color(141, 72, 203, 255),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = urbanist
                    )
                    Text(
                        text = event.first.substring(8,10) + " " + getMonth(event.first.substring(5,7).toInt()) + " " + event.first.substring(0,4) ,
                        color = Color(141, 72, 203, 255),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        fontFamily = urbanist
                    )
                }
            }
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .scale(3f)
                        .clickable {

                        },
                    painter = painterResource(R.drawable.calenderandplus),
                    contentDescription = "Calendar and Plus"
                )
            }
        }
    }
}

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
fun Calender(
    year : Int,
    month: Int,
    date : Int
) {
    val calender = getMonthCalender(year, month)
    var selected by remember { mutableIntStateOf(date) }
    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .height(245.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            Spacer(modifier = Modifier.padding(top = 7.dp))
            val days = listOf("Su" , "Mo" , "Tu" , "We" , "Th" , "Fr" , "Sa")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                days.forEach() { day ->
                    Text(
                        text = day,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = urbanist
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 0.dp))
            Column() {
                calender.forEach { week ->
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        week.forEach { day ->
                            if(day!=0) {
                                if(day == selected){
                                    Surface(
                                        shape = CircleShape,
                                        modifier = Modifier.size(20.dp),
                                        color = Color(142, 72, 203)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            if (day % 10 == day) {
                                                Row() {
                                                    Text(
                                                        text = "0",
                                                        modifier = Modifier.clickable {
                                                            selected = day
                                                        },
                                                        fontSize = 14.sp,
                                                        color = Color.Transparent,
                                                        fontFamily = urbanist
                                                    )
                                                    Text(
                                                        text = day.toString(),
                                                        modifier = Modifier
                                                            .clickable {
                                                                selected = day
                                                            }
                                                            .padding(end = 5.dp),
                                                        fontSize = 14.sp,
                                                        color = Color.White,
                                                        fontFamily = urbanist
                                                    )
                                                }
                                            } else {
                                                Text(
                                                    text = day.toString(),
                                                    modifier = Modifier.clickable {
                                                        selected = day
                                                    },
                                                    fontSize = 14.sp,
                                                    color = Color.White,
                                                    fontFamily = urbanist
                                                )
                                            }
                                        }
                                    }
                                }
                                else {
                                    if (day % 10 == day) {
                                        Row() {
                                            Text(
                                                text = "0",
                                                modifier = Modifier.clickable {
                                                    selected = day
                                                },
                                                fontSize = 14.sp,
                                                color = Color.Transparent,
                                                fontFamily = urbanist
                                            )
                                            Text(
                                                text = day.toString(),
                                                modifier = Modifier.clickable {
                                                    selected = day
                                                },
                                                fontSize = 14.sp,
                                                color = Color.White,
                                                fontFamily = urbanist
                                            )
                                        }
                                    } else {
                                        Text(
                                            text = day.toString(),
                                            modifier = Modifier.clickable {
                                                selected = day
                                            },
                                            fontSize = 14.sp,
                                            color = Color.White,
                                            fontFamily = urbanist
                                        )
                                    }
                                }
                            }
                            else {
                                Text(
                                    text = "00",
                                    modifier = Modifier.clickable {
                                        selected = day
                                    },
                                    fontSize = 14.sp,
                                    color = Color.Transparent,
                                    fontFamily = urbanist
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropDown(
    expanded : MutableState<Boolean>,
    yearsToDisplay : List<Int>,
    year: MutableState<Int>,
    month1 : List<String>,
    month2 : List<String>,
    month3 : List<String>,
    month : MutableState<Int>
) {
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = {
            expanded.value = false
        },
        modifier = Modifier.background(
            color = Color(157, 98, 253).copy(alpha = 0.7f)
        )
    ) {
        DropdownMenuItem(
            modifier = Modifier.width(181.dp),
            text = {
                Box(
                    modifier = Modifier
                        .height(46.dp)
                        .fillMaxWidth()
                ) {
                    val scrollSate = rememberScrollState()
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .horizontalScroll(scrollSate)
                    ) {
                        yearsToDisplay.forEach { y ->
                            Card(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .clickable {
                                        year.value = y
                                        expanded.value = false
                                    }
                                    .width(60.dp),
                                shape = RectangleShape,
                                colors = CardDefaults.cardColors(if(year.value==y) Color.Cyan else Color.White)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = y.toString(),
                                        fontSize = 16.sp,
                                        fontFamily = urbanist
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(start = 5.dp))
                        }
                    }
                }
            },
            onClick = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.padding(top = 25.dp))
        DropdownMenuItem(
            modifier = Modifier.width(181.dp),
            text = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    month1.forEach { m ->
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable {
                                    month.value = getMonthInt(m)
                                    expanded.value = false
                                }
                                .width(35.dp),
                            shape = RectangleShape,
                            colors = CardDefaults.cardColors(if(month.value== getMonthInt(m)) Color.Cyan else Color.White)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = m,
                                    fontFamily = urbanist
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                    }
                }
            },
            onClick = { /*TODO*/ }
        )
        DropdownMenuItem(
            modifier = Modifier.width(181.dp),
            text = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    month2.forEach { m ->
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable {
                                    month.value = getMonthInt(m)
                                    expanded.value = false
                                }
                                .width(35.dp),
                            shape = RectangleShape,
                            colors = CardDefaults.cardColors(if(month.value== getMonthInt(m)) Color.Cyan else Color.White)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = m,
                                    fontFamily = urbanist
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                    }
                }
            },
            onClick = { /*TODO*/ }
        )
        DropdownMenuItem(
            modifier = Modifier.width(181.dp),
            text = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    month3.forEach { m ->
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable {
                                    month.value = getMonthInt(m)
                                    expanded.value = false
                                }
                                .width(35.dp),
                            shape = RectangleShape,
                            colors = CardDefaults.cardColors(if(month.value== getMonthInt(m)) Color.Cyan else Color.White)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = m,
                                    fontFamily = urbanist
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                    }
                }
            },
            onClick = { /*TODO*/ }
        )
    }
}