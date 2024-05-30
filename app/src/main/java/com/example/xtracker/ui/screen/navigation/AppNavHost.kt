package com.example.xtracker.ui.screen.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.xtracker.viewModel.UserDetails
import com.example.xtracker.viewModel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    transactionViewModel: TransactionViewModel,
    userViewModel: UserViewModel,
    userState: MutableState<UserDetails?>,
    innerPadding: PaddingValues
) {
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
            userState.value?.userID?.let { it1 -> AddEntryScreen(transactionViewModel = transactionViewModel, userViewModel = userViewModel, userID = it1) }
        }
        composable("edit/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!.toInt()
            userState.value?.userID?.let { EditEntryScreen(transactionViewModel = transactionViewModel, userViewModel = userViewModel, navController = navController, id = id, userID = it) }
        }
        composable("profile") {

        }
        composable("login") {
            LoginScreen(userViewModel = userViewModel, navController = navController)
        }
        composable("register") {
            RegisterScreen(userViewModel = userViewModel, navController = navController)
        }
    }
}