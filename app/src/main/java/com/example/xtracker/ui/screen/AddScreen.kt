package com.example.xtracker.ui.screen

import DropdownMenuDemo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.example.xtracker.model.Category
import com.example.xtracker.model.TransactionType
import com.example.xtracker.model.models.Transaction
import com.example.xtracker.viewModel.TransactionViewModel
import com.example.xtracker.viewModel.UserViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEntryScreen(transactionViewModel: TransactionViewModel?, userViewModel: UserViewModel, userID: Int) {
    var selectedType by remember { mutableStateOf("Income") }
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Food") }
    var showConfirmation by remember { mutableStateOf(false) }
    val categories = Category.entries.map { it.category }

    val scope = rememberCoroutineScope()

    println(userID)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New Transaction", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        DropdownMenuDemo(
            label = "Select Type",
            items = listOf(TransactionType.INCOME.type, TransactionType.EXPENSES.type, TransactionType.SAVINGS.type),
            selectedItem = selectedType,
            onItemSelected = { selectedType = it }
        )

        if (selectedType == TransactionType.EXPENSES.type) {
            DropdownMenuDemo(
                label = "Select Category",
                items = categories,
                selectedItem = selectedCategory,
                onItemSelected = { selectedCategory = it }
            )
        }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Button(
            onClick = {
                scope.launch {
                    showConfirmation = true
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit")
        }

        if (showConfirmation) {
            Dialog(onDismissRequest = { showConfirmation = false }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val currentDate = LocalDateTime.now().format(formatter)
                        val regex = "^(?!\\.)[0-9]*\\.?[0-9]+$".toRegex()
                        if (regex.matches(amount)) {
                            val newTransaction = Transaction(amount = amount.toDouble(), type = selectedType, date = currentDate, category = selectedCategory, userID = userID)
                            transactionViewModel!!.addTransaction(newTransaction)

                            Text("Transaction added successfully!")
                        } else {
                            Text("Amount is invalid or empty!")
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            showConfirmation = false
                        }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}


