package com.example.xtracker.ui.screen.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.xtracker.ui.screen.AddEntryScreen
import com.example.xtracker.ui.screen.Dashboard
import com.example.xtracker.ui.screen.EditEntryScreen
import com.example.xtracker.ui.screen.ExpensesScreen
import com.example.xtracker.ui.screen.IncomeScreen
import com.example.xtracker.ui.screen.SavingsScreen
import com.example.xtracker.viewModel.CategoryViewModel
import com.example.xtracker.viewModel.TransactionViewModel
import com.example.xtracker.viewModel.toTransactionDetails

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController, transactionViewModel: TransactionViewModel, categoryViewModel: CategoryViewModel, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "dashboard", modifier = androidx.compose.ui.Modifier.padding(
        innerPadding
    )) {
        composable("dashboard") {
            println(transactionViewModel.totalIncomeState)
            Dashboard(navController = navController, transactionViewModel = transactionViewModel)
        }
        composable("expenses") {
            ExpensesScreen(navController = navController, transactionViewModel = transactionViewModel)
        }
        composable("income") {
            IncomeScreen(navController = navController, transactionViewModel = transactionViewModel)
        }
        composable("savings") {
            SavingsScreen(navController = navController, transactionViewModel = transactionViewModel)
        }
        composable("add"){
            AddEntryScreen(transactionViewModel = transactionViewModel, categoryViewModel = categoryViewModel)
        }
        composable("edit/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!.toInt()
            EditEntryScreen(transactionViewModel = transactionViewModel, categoryViewModel = categoryViewModel, navController = navController, id = id)
        }
    }
}