package com.example.xtracker.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.xtracker.ui.utils.getIconForType
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
            Icon(imageVector = Icons.Default.Person, contentDescription = "Icon", modifier = Modifier.size(40.dp))
            Text(
                text = "User Profile",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Card(
                modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),

            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    if (userState != null) {
                        Text(
                            text = "Username: ${userState?.username ?: "N/A"}",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Email: ${userState?.email ?: "N/A"}",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(text = "Loading...", fontSize = 20.sp)
                    }
                }
            }
        }
    }
}