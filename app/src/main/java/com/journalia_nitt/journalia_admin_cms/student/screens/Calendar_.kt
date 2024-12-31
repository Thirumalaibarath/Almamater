package com.journalia_nitt.journalia_admin_cms.student.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.journalia_nitt.journalia_admin_cms.student.days
import com.journalia_nitt.journalia_admin_cms.ui.theme.color
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth


@Composable
fun calendarScreen(): LocalDate {
    val selectColor = Color(5, 3, 153).copy(alpha = 0.25f)
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf(today) }
    var selectedMonth by remember { mutableStateOf("") }
    val initialYear =  Year.now().minusYears(10).value
    val finalYear = Year.now().plusYears(10).value
    val startMonth = YearMonth.now()
    val state = rememberCalendarState(
        startMonth = YearMonth.of(initialYear,12),
        endMonth = YearMonth.of(finalYear,12),
        firstVisibleMonth = startMonth,
        firstDayOfWeek = firstDayOfWeekFromLocale(),
        outDateStyle = OutDateStyle.EndOfRow
    )
    val gradient = Brush.linearGradient(
        colors = listOf(Color(150, 103, 224), Color(188, 128, 240))
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(
            modifier = Modifier.padding(0.dp,10.dp,0.dp,10.dp).border(1.5.dp,Color.Black, shape = RoundedCornerShape(12.dp))
        )
        {
            Text(
                text = state.firstVisibleMonth.yearMonth.month.toString() + " " + state.firstVisibleMonth.yearMonth.year,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Column(modifier = Modifier.padding(10.dp,0.dp).background(brush = gradient,shape = RoundedCornerShape(16.dp)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp,10.dp), horizontalArrangement = Arrangement.SpaceAround
            )
            {
                for(i in days)
                {
                    Box(
                        modifier = Modifier.width(50.dp)
//                            .border(2.dp,Color.Black)
                        , contentAlignment = Alignment.Center
                    )
                    {
                        Text(text = i,  fontSize = 16.sp,
                            color = Color.White, fontFamily = urbanist, fontWeight = FontWeight.Bold)

                    }
                }
            }
            HorizontalCalendar(
                state = state,
                modifier = Modifier
//                .border(2.dp,Color.Black)
                ,
                calendarScrollPaged = true,
                userScrollEnabled = true,
                dayContent = { day ->
                    if(day.date.month == state.firstVisibleMonth.yearMonth.month)
                    {
                        Box(
                            modifier = Modifier
                                .size(45.dp)
//                                .border(2.dp,Color.Black)
                                .background(if (day.date == today) color else if(day.date == selectedDate)  selectColor  else Color.Transparent, shape = CircleShape)
                                .padding(5.dp)
                                .clickable {
                                    selectedDate = day.date
                                    selectedMonth = state.firstVisibleMonth.toString()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.date.dayOfMonth.toString(),
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center),
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                },
                )
        }
    }
    return selectedDate
}