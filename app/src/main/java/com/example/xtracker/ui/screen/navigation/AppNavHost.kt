package com.example.xtracker.ui.screen.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.xtracker.model.TransactionType
import com.example.xtracker.ui.screen.AddEntryScreen
import com.example.xtracker.ui.screen.Dashboard
import com.example.xtracker.ui.screen.EditEntryScreen
import com.example.xtracker.ui.screen.LoginScreen
import com.example.xtracker.ui.screen.RegisterScreen
import com.example.xtracker.ui.screen.TransactionsScreen
import com.example.xtracker.viewModel.TransactionViewModel
import com.example.xtracker.viewModel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController, transactionViewModel: TransactionViewModel, userViewModel: UserViewModel, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "login", modifier = androidx.compose.ui.Modifier.padding(
        innerPadding
    )) {
        composable("dashboard") {
            Dashboard(navController = navController, transactionViewModel = transactionViewModel)
        }
        composable("expenses") {
            TransactionsScreen(navController = navController, transactionViewModel = transactionViewModel, transactionType = TransactionType.EXPENSES)
        }
        composable("income") {
            TransactionsScreen(navController = navController, transactionViewModel = transactionViewModel, transactionType = TransactionType.INCOME)
        }
        composable("savings") {
            TransactionsScreen(navController = navController, transactionViewModel = transactionViewModel, transactionType = TransactionType.SAVINGS)
        }
        composable("add"){
            AddEntryScreen(transactionViewModel = transactionViewModel)
        }
        composable("edit/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!.toInt()
            EditEntryScreen(transactionViewModel = transactionViewModel, navController = navController, id = id)
        }
        composable("profile") {

        }
        composable("logout") {
            userViewModel.logout()
            navController.navigate("login")
        }
        composable("login") {
            LoginScreen(userViewModel = userViewModel, navController = navController)
        }
        composable("register") {
            RegisterScreen(userViewModel = userViewModel, navController = navController)
        }
    }
}