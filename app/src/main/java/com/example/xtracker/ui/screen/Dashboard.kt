package com.example.xtracker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.xtracker.ui.theme.background
import android.graphics.Paint.Align
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.R
import com.example.xtracker.model.Category
import com.example.xtracker.model.CategoryOBJ
import com.example.xtracker.model.Type
import com.example.xtracker.model.TypeOBJ
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.reflect.typeOf

//TEST DASHBOARD BRANCH

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = background) // Apply the background color here
    ){
        //val state = rememberDatePickerState()
        //var openCalendar by remember { mutableStateOf(false) }
        //var showDate by remember { mutableStateOf(false) }

        val logo: Painter = painterResource(id = R.drawable.logo)

        // Define what should happen when the menu is clicked
        val onMenuClick: () -> Unit = {
            // Handle the menu click action here
            // For example, you can open a menu or navigate to another screen
        }

        // Now, call the Footer composable and pass the logo and onMenuClick lambda
        Footer(logo = logo, onMenuClick = onMenuClick)

        Spacer(modifier = Modifier
            .height(50.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*
            Icon(
                Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .clickable { openCalendar = true }
                    .align(Alignment.Start)
            )

             */
            Spacer(modifier = Modifier.height(10.dp))
            /*
            if(openCalendar){
                DatePickerDialog(
                    onDismissRequest = { openCalendar = false },
                    confirmButton = {
                        Button(onClick = { showDate = true ;openCalendar = false  }) {
                            Text(text = "Confirm")
                        }
                    },
                )
                {
                    DatePicker(
                        state = state
                    )
                }
            }

             */
            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Types", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))
            LazyRow {
                items(TypeOBJ.types) { type ->
                    TypeCard(type = type ) //kartica - TypeCard
                }
            }
            Divider(modifier = Modifier.padding(15.dp))

            Text(text = "Last transactions", fontSize = 20.sp,modifier = Modifier.align(Alignment.Start))

            /*
            if(showDate){
                //format date from calendar to String
                val formatter: SimpleDateFormat =  SimpleDateFormat("yyyy-MM-dd")
                val dateString = formatter.format(state.selectedDateMillis)

                //format date from string to LocalDate
                Text(text = "${LocalDate.parse(dateString).year.toString()} ${LocalDate.parse(dateString).month.toString()} ${LocalDate.parse(dateString).dayOfMonth.toString()}")
            }
            */


            LazyColumn {
                items(CategoryOBJ.categories){
                        category ->
                    CategoryCard(category = category) //CategoriesCard --> kartica
                }
            }
        }
    }
}


@Composable
fun TypeCard(type: Type){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(230.dp)
            .height(110.dp)
    ){
        Row (
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ){
            Image(
                painter = painterResource(id = type.image),
                contentDescription = "Type",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column{
                Text(text = type.name, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = type.total.toString(), fontSize = 16.sp)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CategoryCard(category: Category){
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    //val date = LocalDate.parse(Category.date, formatter)

    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ){
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top=5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        )
        {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = "Category",
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(3f),){
                Text(text = category.name, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = category.amount.toString() + "BAM",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    //val date = category.date
                    Text(text = category.date.toString() ,fontSize = 10.sp,
                    fontWeight = FontWeight.Light)
                }
            }
        }
    }
}

@Composable
fun Footer(
    logo: Painter,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable { onMenuClick() }
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreenPreview() {
    Dashboard()
}

