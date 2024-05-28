package com.example.xtracker.ui.screen

import TransactionCard
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import com.example.xtracker.model.TransactionType
import com.example.xtracker.viewModel.TransactionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavingsScreen(transactionViewModel: TransactionViewModel?, navController: NavHostController) {
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
                    transaction -> TransactionCard(transaction = transaction, transactionViewModel = transactionViewModel, navController = navController)
            }
        }
    }
}


