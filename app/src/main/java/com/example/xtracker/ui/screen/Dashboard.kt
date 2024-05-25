package com.example.xtracker.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.model.Transaction
import com.example.xtracker.model.Transactions
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dashboard(){
    val income = 100
    val expense = 200
    val savings = 500

    val items = listOf(
        Triple("Income", income, income.toString()),
        Triple("Expenses", expense, expense.toString()),
        Triple("Savings", savings, savings.toString())
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyRow (
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(items) { item ->
                TotalCard(title = item.first, amount = item.second, displayAmount = item.third)
            }
        }

        Divider(modifier = Modifier.padding(15.dp))

        Text(text = "Latest transactions", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))

        LazyColumn (
            modifier = Modifier
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(Transactions.transactions){
                transactions -> TransactionCard(transaction = transactions)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DashboardPreview(){
    Dashboard()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionCard(transaction : Transaction){
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(transaction.date, formatter)
    
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(110.dp)
    ){
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top=5.dp, bottom = 5.dp,start=5.dp, end=10.dp)
        ){
            /*
            Image(
                painter = painterResource(id = //ovdje ide slika),
                contentDescription = "NESTO",
                    modifier = Modifier.size(100.dp)
                    contentScale = ContentScale.Crop
            )
            */
            Spacer(modifier = Modifier.width(20.dp))
            Column{
                Text(text = transaction.type.toString())
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = transaction.amount.toString() + "USD",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = if(transaction.amount<0){
                            Color.Red
                        }
                        else{
                            Color.Green
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "${date.month.value.toString()}.${date.dayOfMonth.toString()}.${date.year.toString()}", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun TotalCard(title:String, amount: Int, displayAmount: String){
    Card(
        modifier = Modifier
            .height(110.dp)
            .width(150.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = displayAmount, color = Color.White, fontSize = 16.sp)
        }
    }
}