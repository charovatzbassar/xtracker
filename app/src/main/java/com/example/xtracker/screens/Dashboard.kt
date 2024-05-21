package com.example.xtracker.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.xtracker.model.Categories
import com.example.xtracker.model.Transaction
import com.example.xtracker.model.Category
import com.example.xtracker.model.Transactions
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(){
    val state = rememberDatePickerState()
    var openCalendar by remember { mutableStateOf(false) }
    var showDate by remember { mutableStateOf(false) }

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

        Text(text = "Courses", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))
        LazyRow {
            items(Categories.categories) { category ->
                CategoyCard(category = category)
            }
        }

        Divider(modifier = Modifier.padding(15.dp))

        Text(text = "Attendance", fontSize = 20.sp,modifier = Modifier.align(Alignment.Start))

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
            items(Transactions.transactions){
                    transaction ->
                TransactionCard(transaction = transaction)
            }
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    //val date = LocalDate.parse(transaction.date, formatter)


    val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    try {
        // Parse the transaction date
        val date = LocalDate.parse(transaction.date, formatter)
        println("Parsed date: $date")
    } catch (e: DateTimeParseException) {
        println("Error parsing date: ${e.message}")
    }


    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(IntrinsicSize.Min),
        //colors = CardDefaults.cardColors(
        //containerColor = Color.LightGray,
        //)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ) {
            /*
            Image(
                painter = painterResource(id = transaction.category.icon),
                contentDescription = "IBU",
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

             */
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(3f),) {
                //Text(text = transaction.type, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = transaction.amount.toString() + "USD",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                        /*
                    color = if(attendance.percentage < 55){
                        Color.Red
                    }else{
                        Color.Blue
                    }
                    */
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    //Text(text = "${date.month.value.toString()}.${date.dayOfMonth.toString()}.${date.year.toString()}",fontSize = 10.sp,
                    //    fontWeight = FontWeight.Light)
                }
            }
        }
    }
}



@Composable
fun CategoyCard(category: Category) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(110.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ) {
            /*
            Image(
                painter = painterResource(id = category.icon), //ovdje trevaju tri različite ikone, jedna za income, jedna za expense i jedna za saving, so depending on the category, ta ikonica ide
                contentDescription = "IBU",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

             */
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                //Text(text = Category., fontSize = 18.sp)
                //Text(text = "Test", fonzSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                //Text(text = course.code, fontSize = 16.sp)
            }
        }
    }

    /*
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun TransactionCard(transaction: Transaction) {
        //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        //val date = LocalDate.parse(transaction.date, formatter)


        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        try {
            // Parse the transaction date
            val date = LocalDate.parse(transaction.date, formatter)
            println("Parsed date: $date")
        } catch (e: DateTimeParseException) {
            println("Error parsing date: ${e.message}")
        }


        Card(
            modifier = Modifier
                .padding(5.dp)
                .width(350.dp)
                .height(IntrinsicSize.Min),
            //colors = CardDefaults.cardColors(
            //containerColor = Color.LightGray,
            //)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
            ) {
                /*
                Image(
                    painter = painterResource(id = transaction.category.icon),
                    contentDescription = "IBU",
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )

                 */
                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier.weight(3f),) {
                    //Text(text = transaction.type, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = transaction.amount.toString() + "USD",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                            /*
                        color = if(attendance.percentage < 55){
                            Color.Red
                        }else{
                            Color.Blue
                        }
                        */
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        //Text(text = "${date.month.value.toString()}.${date.dayOfMonth.toString()}.${date.year.toString()}",fontSize = 10.sp,
                        //    fontWeight = FontWeight.Light)
                    }
                }
            }
        }
    }
}

     */

//val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    //val date = LocalDate.parse(transaction.date, formatter)


    val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    try {
        // Parse the transaction date
        //val date = LocalDate.parse(transaction.date, formatter)
        //println("Parsed date: $date")
    } catch (e: DateTimeParseException) {
        println("Error parsing date: ${e.message}")
    }


    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(IntrinsicSize.Min),
        //colors = CardDefaults.cardColors(
        //containerColor = Color.LightGray,
        //)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ) {
            /*
            Image(
                painter = painterResource(id = transaction.category.icon),
                contentDescription = "IBU",
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

             */
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(3f),) {
                Text(text = category.name, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = category.name + "USD",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                        /*
                    color = if(attendance.percentage < 55){
                        Color.Red
                    }else{
                        Color.Blue
                    }
                    */
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    //Text(text = "${date.month.value.toString()}.${date.dayOfMonth.toString()}.${date.year.toString()}",fontSize = 10.sp,
                    //    fontWeight = FontWeight.Light)
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardPreview(){
    //CourseCard(CoursesIBU.courses[0])
    //AttendanceCard(AttendanceIBU.attendance[0])
    TransactionCard(transaction = Transactions.transactions[0])
    CategoyCard(category = Categories.categories[0])
    Dashboard()
}



