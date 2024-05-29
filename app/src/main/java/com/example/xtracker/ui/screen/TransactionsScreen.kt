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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.xtracker.model.TransactionType
import com.example.xtracker.ui.utils.getIconForType
import com.example.xtracker.viewModel.TransactionViewModel
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionsScreen(transactionViewModel: TransactionViewModel?, navController: NavHostController, transactionType: TransactionType) {
    val total = when (transactionType) {
        TransactionType.EXPENSES -> transactionViewModel!!.totalExpenseState
        TransactionType.INCOME -> transactionViewModel!!.totalIncomeState
        TransactionType.SAVINGS -> transactionViewModel!!.totalSavingState
    }

    val transactions = transactionViewModel.transactionUIState.transactions
        .filter { it!!.type == transactionType.type }
        .sortedByDescending { it!!.date }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Icon(imageVector = getIconForType(transactionType.type), contentDescription = "Icon", modifier = Modifier.size(40.dp))
        Text(
            text = "Total ${transactionType.type}: ",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(text = "\$$total", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = if (transactionType == TransactionType.EXPENSES) {
            MaterialTheme.colorScheme.error
        } else MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        if (transactions.isEmpty()) {
            Text(text = "There are no ${transactionType.type.lowercase(Locale.ROOT)} transactions.")
        } else {
            LazyColumn (
                modifier = Modifier
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactions){
                        transaction -> TransactionCard(transaction = transaction, transactionViewModel = transactionViewModel, navController = navController)
                }
            }
        }

    }
}
