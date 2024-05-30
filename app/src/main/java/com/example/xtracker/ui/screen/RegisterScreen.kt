package com.example.xtracker.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.xtracker.R
import com.example.xtracker.viewModel.UserDetails
import com.example.xtracker.viewModel.UserViewModel

@Composable
fun RegisterScreen(userViewModel: UserViewModel, navController: NavHostController) {
    val username = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register to XTracker!")
        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = email.value,
            onValueChange = { value -> email.value = value },
            enabled = true,
            label = {
                Text(text = "Email")
            },
            placeholder = {
                Text(text = "Your email")
            },
            isError = false
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = username.value,
            onValueChange = { value -> username.value = value },
            enabled = true,
            label = {
                Text(text = "Username")
            },
            placeholder = {
                Text(text = "Your username")
            },
            isError = false
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = password.value,
            onValueChange = { value -> password.value = value },
            enabled = true,
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Your password")
            },
            isError = false,
            visualTransformation = PasswordVisualTransformation()
            )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))

        TextButton(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Already have an account?", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Button(
            onClick = {
                if (username.value != "" && password.value != "") {
                    val newUser = UserDetails(username = username.value, email = email.value, password = password.value)
                    userViewModel.register(newUser)
                }
            }
            ) {
            Text(
                text = "Register",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }
    }
}
