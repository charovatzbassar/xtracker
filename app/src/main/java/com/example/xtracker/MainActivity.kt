package com.example.xtracker

import MyAppBar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xtracker.ui.screen.AddEntryScreen
import com.example.xtracker.ui.screen.Dashboard
import com.example.xtracker.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

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
                                        //Text(text = "Dashboard")
                                        Dashboard(navController = navController)
                                    }
                                    composable("expenses") {
                                        Text(text = "Expenses that I made for testing purposes")
                                    }
                                    composable("income") {
                                        Text(text = "Income")
                                    }
                                    composable("savings") {
                                        Text(text = "Savings")
                                    }
                                    composable("add"){
                                        AddEntryScreen()
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
