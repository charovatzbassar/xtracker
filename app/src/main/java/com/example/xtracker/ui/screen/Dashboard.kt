package com.example.xtracker.ui.screen

import TotalCard
import TransactionCard
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xtracker.viewModel.TransactionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dashboard(navController: NavHostController, transactionViewModel: TransactionViewModel?){
    val income = transactionViewModel?.totalIncomeState
    val expense = transactionViewModel?.totalExpenseState
    val savings = transactionViewModel?.totalSavingState

    val items = listOf(
        Triple("Income", income, income.toString()),
        Triple("Expenses", expense, expense.toString()),
        Triple("Savings", savings, savings.toString())
    )

    val transactions = transactionViewModel!!.transactionUIState.transactions

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
                TotalCard(
                    title = item.first,
                    amount = item.second,
                    displayAmount = item.third,
                    onClick = {
                        when (item.first) {
                            "Income" -> navController.navigate("income")
                            "Expenses" -> navController.navigate("expenses")
                            "Savings" -> navController.navigate("savings")
                        }
                    }
                )
            }
        }

        Divider(modifier = Modifier.padding(15.dp))

        Text(text = "Latest transactions", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))

        LazyColumn (
            modifier = Modifier
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(transactions){
                transaction -> TransactionCard(transaction = transaction, transactionViewModel = transactionViewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DashboardPreview(){
    val navController = rememberNavController()
    Dashboard(navController = navController, transactionViewModel = null)
}



