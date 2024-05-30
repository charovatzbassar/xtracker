package com.example.xtracker

import MyAppBar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.xtracker.model.AppContainer
import com.example.xtracker.model.AppDataContainer
import com.example.xtracker.model.TransactionType
import com.example.xtracker.ui.composable.MenuItem
import com.example.xtracker.ui.composable.NavigationDrawer
import com.example.xtracker.ui.screen.navigation.AppNavHost
import com.example.xtracker.ui.theme.XTrackerTheme
import com.example.xtracker.viewModel.TransactionViewModel
import com.example.xtracker.viewModel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var container: AppContainer
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        container = AppDataContainer(this)
        installSplashScreen()


        super.onCreate(savedInstanceState)

        setContent {
            XTrackerTheme {
                var isLoggedIn = remember {
                    mutableStateOf(false)
                }

                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val userViewModel = UserViewModel(userRepository = container.userRepository, navController = navController, isLoggedIn = isLoggedIn)

                val transactionViewModel = TransactionViewModel(transactionRepository = container.transactionRepository, userID = userViewModel.userDetailsState.userID)
                transactionViewModel.getTotalForType(TransactionType.EXPENSES.type, userViewModel.userDetailsState.userID)
                transactionViewModel.getTotalForType(TransactionType.INCOME.type, userViewModel.userDetailsState.userID)
                transactionViewModel.getTotalForType(TransactionType.SAVINGS.type, userViewModel.userDetailsState.userID)



                    ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                                if (isLoggedIn.value) {
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
                                                title = "Add Transaction",
                                                contentDescription = "Go to add entry screen",
                                                icon = Icons.Default.Add
                                            ),
                                            MenuItem(
                                                id = "profile",
                                                title = "Profile",
                                                contentDescription = "Go to profile screen",
                                                icon = Icons.Default.Person
                                            ),
                                            MenuItem(
                                                id = "logout",
                                                title = "Log out",
                                                contentDescription = "Logout the user",
                                                icon = Icons.Default.ExitToApp
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
                            }

                    },
                    content = {
                        Scaffold(
                            topBar = {
                                if (isLoggedIn.value) {
                                    MyAppBar(
                                        onNavigationIconClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    )
                                }
                            },
                            content = { innerPadding ->
                                AppNavHost(
                                    navController = navController,
                                    transactionViewModel = transactionViewModel,
                                    userViewModel = userViewModel,
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
