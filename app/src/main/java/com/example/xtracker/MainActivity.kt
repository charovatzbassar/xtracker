package com.example.xtracker

import MyAppBar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.xtracker.model.AppContainer
import com.example.xtracker.model.AppDataContainer
import com.example.xtracker.model.TransactionType
import com.example.xtracker.ui.composable.MenuItem
import com.example.xtracker.ui.composable.NavigationDrawer
import com.example.xtracker.ui.screen.navigation.AppNavHost
import com.example.xtracker.ui.theme.NavigationDrawerComposeTheme
import com.example.xtracker.viewModel.TransactionViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var container: AppContainer
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        container = AppDataContainer(this)
        installSplashScreen()

        val transactionViewModel = TransactionViewModel(transactionRepository = container.transactionRepository)
        transactionViewModel.getTotalForType(TransactionType.EXPENSES.type)
        transactionViewModel.getTotalForType(TransactionType.INCOME.type)
        transactionViewModel.getTotalForType(TransactionType.SAVINGS.type)


        super.onCreate(savedInstanceState)

        setContent {
            NavigationDrawerComposeTheme {
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                    ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                                NavigationDrawer(
                                    items = listOf(
                                        MenuItem(
                                            id = "dashboard",
                                            title = "Dashboard",
                                            contentDescription = "Go to dashboard screen",
                                            icon = Icons.Default.Home
                                        ),
                                        MenuItem(
                                            id = "income",
                                            title = "Income",
                                            contentDescription = "Go to income screen",
                                            icon = Icons.Default.KeyboardArrowUp
                                        ),
                                        MenuItem(
                                            id = "expenses",
                                            title = "Expenses",
                                            contentDescription = "Go to expenses screen",
                                            icon = Icons.Default.KeyboardArrowDown
                                        ),
                                        MenuItem(
                                            id = "savings",
                                            title = "Savings",
                                            contentDescription = "Go to savings screen",
                                            icon = Icons.Default.Lock
                                        ),
                                        MenuItem(
                                            id = "add",
                                            title = "Add Entry",
                                            contentDescription = "Go to add entry screen",
                                            icon = Icons.Default.Add
                                        ),
                                    ),
                                    onItemClick = {
                                        navController.navigate(it.id)
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                )
                            }

                    },
                    content = {
                        Scaffold(
                            topBar = {
                                MyAppBar(
                                    onNavigationIconClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            },
                            content = { innerPadding ->
                                AppNavHost(
                                    navController = navController,
                                    transactionViewModel = transactionViewModel,
                                    innerPadding = innerPadding
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
