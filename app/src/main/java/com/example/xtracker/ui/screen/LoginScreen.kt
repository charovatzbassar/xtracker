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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.R

@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo128px),
            contentDescription = "",
            modifier = Modifier.size(width = 100.dp, height = 100.dp)
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = "",
            onValueChange = { /* TODO */ },
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
            value = "",
            onValueChange = { /* TODO */},
            label = {
                Text(text = "Password")
            },
            isError = false,
            //trailingIcon = {  Icon(painter = painterResource(id = Icons.Default.Create), contentDescription = "") }
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Do not have an account?")
        }

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }

    }
}
