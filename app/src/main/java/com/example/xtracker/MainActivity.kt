package com.example.xtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xtracker.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                         AppBar(
                             onNavigationIconClick = {
                                 scope.launch {
                                     scaffoldState.drawerState.open()
                                 }
                             }
                         )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    content = {
                        NavHost(navController = navController, startDestination = "dashboard") {
                            composable("dashboard") {
                                Text(text = "Dashboard")
                            }
                            composable("expenses") {
                                Text(text = "Expenses")
                            }
                            composable("income") {
                                Text(text = "Income")
                            }
                            composable("savings") {
                                Text(text = "Savings")
                            }
                        }
                    },
                    drawerContent = {
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
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                )


            }
        }
    }
}
