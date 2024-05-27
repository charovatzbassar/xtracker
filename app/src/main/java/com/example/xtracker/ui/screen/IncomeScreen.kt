package com.example.xtracker.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.model.TransactionType
import com.example.xtracker.viewModel.TransactionDetails
import com.example.xtracker.viewModel.TransactionViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(transactionViewModel: TransactionViewModel?) {
    var totalIncome by remember { mutableDoubleStateOf(0.0) }

    val incomeTransactions = transactionViewModel!!.transactionUIState.transactions
        .filter { it!!.type == TransactionType.INCOME.type }
        .sortedByDescending { it!!.date }

    LaunchedEffect(Unit) {
        totalIncome = incomeTransactions.sumOf { it!!.amount }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .width(350.dp)
                .height(150.dp)
                .padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE0F7FA)
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "$totalIncome USD",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE0F7FA)
            )
        ) {
            LazyColumn (
                modifier = Modifier
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(incomeTransactions){
                        transaction -> TransactionCard(transaction = transaction)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IncomeItem(transaction: TransactionDetails?) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(transaction?.date, formatter)

    val expenseAmountColor = Color(0xFF4CAF50)  // Green color for the amount
    val dateTextColor = Color.Gray  // Gray color for the date and time

    val incomeTypes = listOf("Paycheck", "Booking", "Freelance", "Investment", "Rental", "Sale", "Dividend")

    Row(
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = incomeTypes.random(), // Randomly selecting income type from the list
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "${date.month.value}.${date.dayOfMonth}.${date.year}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = dateTextColor
            )
        }
        Text(
            text = "+${transaction?.amount} USD",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = expenseAmountColor
        )
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun IncomeScreenPreview() {
    IncomeScreen(transactionViewModel = null)
}