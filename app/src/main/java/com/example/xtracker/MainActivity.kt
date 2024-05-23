package com.example.xtracker

import MyAppBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.ExpensesScreen
import com.example.xtracker.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
                            Column {
                                DrawerHeader()
                                DrawerBody(
                                    items = listOf(
                                        MenuItem(
                                            id = "dashboard",
                                            title = "Dashboard",
                                            contentDescription = "Go to dashboard screen",
                                            icon = Icons.Default.DateRange
                                        ),
                                        MenuItem(
                                            id = "income",
                                            title = "Income",
                                            contentDescription = "Go to income screen",
                                            icon = Icons.Default.Email
                                        ),
                                        MenuItem(
                                            id = "expenses",
                                            title = "Expenses",
                                            contentDescription = "Go to expenses screen",
                                            icon = Icons.Default.ShoppingCart
                                        ),
                                        MenuItem(
                                            id = "savings",
                                            title = "Savings",
                                            contentDescription = "Go to savings screen",
                                            icon = Icons.Default.Lock
                                        ),
                                    ),
                                    onItemClick = {
                                        println("Clicked on ${it.id}")
                                        navController.navigate(it.id)
                                        scope.launch {
                                            println("Close drawer")
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
                                MyAppBar(
                                    onNavigationIconClick = {
                                        scope.launch {
                                            println("Open drawer")
                                            drawerState.open()
                                        }
                                    }
                                )
                            },
                            content = { innerPadding ->
                                NavHost(navController = navController, startDestination = "dashboard", modifier = Modifier.padding(innerPadding)) {
                                    composable("dashboard") {
                                        Text(text = "Dashboard")
                                    }
                                    composable("expenses") {
                                        ExpensesScreen()
                                    }
                                    composable("income") {
                                        Text(text = "Income")
                                    }
                                    composable("savings") {
                                        Text(text = "Savings")
                                    }
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}
