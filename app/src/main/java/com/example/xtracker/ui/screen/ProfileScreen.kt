package com.example.xtracker.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.xtracker.viewModel.UserViewModel

@Composable
fun ProfileScreen(userViewModel: UserViewModel, navController: NavHostController) {
    val userState by userViewModel.userState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.padding(vertical = 8.dp),

            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (userState != null) {
                        Text(
                            text = "Username: ${userState?.username ?: "N/A"}",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Email: ${userState?.email ?: "N/A"}",
                            fontSize = 20.sp
                        )
                    } else {
                        Text(text = "Loading...", fontSize = 20.sp)
                    }
                }
            }

            Button(
                onClick = {
                    navController.navigate("dashboard") // Navigate back to dashboard
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(text = "Back to Dashboard")
            }
        }
    }
}