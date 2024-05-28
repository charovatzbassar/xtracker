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
fun SavingsScreen(transactionViewModel: TransactionViewModel?) {
    val totalSavings = transactionViewModel!!.totalSavingState

    val savingsTransactions = transactionViewModel.transactionUIState.transactions
        .filter { it!!.type == TransactionType.SAVINGS.type }
        .sortedByDescending { it!!.date }


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

        LazyColumn (
            modifier = Modifier
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(savingsTransactions){
                    transaction -> TransactionCard(transaction = transaction)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SavingsScreenPreview() {
    SavingsScreen(transactionViewModel = null)
}
