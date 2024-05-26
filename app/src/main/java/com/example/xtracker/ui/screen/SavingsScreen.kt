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
import com.example.xtracker.model.Transaction
import com.example.xtracker.model.Transactions
import com.example.xtracker.model.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingsScreen() {
    var totalSavings by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(Unit) {
        totalSavings = Transactions.transactions.filter { it.type == Type.SAVING }
            .sumOf { it.amount }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Total Savings: $totalSavings",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(Transactions.transactions.filter { it.type == Type.SAVING }) { transaction ->
                SavingsItem(transaction)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavingsItem(transaction: Transaction) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(transaction.date, formatter)

    val cardBackgroundColor = Color(0xFFE0F7FA) // Light Yellow for savings
    val savingsAmountColor = Color(0xFFFFA000) // Amber for the amount text

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${transaction.amount} USD",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = savingsAmountColor
                )
                Text(
                    text = "${date.month.value}.${date.dayOfMonth}.${date.year}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
                Text(
                    text = transaction.category.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SavingsScreenPreview() {
    SavingsScreen()
}
