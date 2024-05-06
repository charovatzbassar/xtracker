package com.example.xtracker.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.R
import com.example.xtracker.ui.theme.XTrackerTheme
import com.example.xtracker.ui.theme.background

@Composable
fun LoginScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background) // Apply the background color here
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(width = 100.dp, height = 100.dp)
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                fontFamily = FontFamily.Default,
                color = Color.Black
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            TextField(
                value = "",
                onValueChange = { /* TODO */ },
                enabled = true,
                label = {
                    Text(text = "email")
                },
                placeholder = {
                    Text(text = "example@exmaple.com")
                },
                isError = false
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            TextField(
                value = "",
                onValueChange = { /* TODO */},
                label = {
                    Text(text = "password")
                },
                isError = false,
                trailingIcon = {
                    //Icon(painter = painterResource(id = R.drawable.show),
                    //   contentDescription = "")

                }
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Do not have an account")
            }

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                )
            }
        }
    }
}


//, uiMode = Configuration.UI_MODE_NIGHT_YES --> ovo ide poslije showBackground = true
@Preview( showBackground = true)
@Composable
fun LoginScreenPreview(){
    XTrackerTheme{
        LoginScreen()
    }
}