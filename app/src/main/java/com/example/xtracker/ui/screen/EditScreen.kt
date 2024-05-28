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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavHostController
import com.example.xtracker.model.Category
import com.example.xtracker.model.models.Transaction
import com.example.xtracker.viewModel.TransactionViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditEntryScreen(transactionViewModel: TransactionViewModel?, navController: NavHostController, id: Int) {

    var transaction: Transaction? by remember {
        mutableStateOf(null)
    }

    println(transaction?.transactionID)

    var selectedType: String? by remember { mutableStateOf("Expenses") }
    var amount: String? by remember { mutableStateOf("") }
    var selectedCategory: String? by remember { mutableStateOf("Food") }
    var showConfirmation by remember { mutableStateOf(false) }
    val categories = Category.entries.map { it.category }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        transaction = transactionViewModel?.getTransactionById(id)
        selectedType = transaction?.type
        amount = transaction?.amount.toString()
        selectedCategory = categories.find { it == transaction?.category }
    }

    println(selectedCategory)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edit Entry", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        DropdownMenuDemo(
            label = "Select Type",
            items = listOf("Income", "Expenses", "Savings"),
            selectedItem = selectedType,
            onItemSelected = { selectedType = it }
        )

        if (selectedType == "Expenses") {
            DropdownMenuDemo(
                label = "Select Category",
                items = categories,
                selectedItem = selectedCategory,
                onItemSelected = { selectedCategory = it }
            )
        }

        amount?.let {
            OutlinedTextField(
                value = it,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }

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

                        if (amount != "") {
                            val newTransaction = amount?.let { selectedType?.let { it1 -> transaction?.date?.let { it2 -> transaction?.transactionID?.let { it3 -> selectedCategory?.let { it4 -> Transaction(amount = it.toDouble(), type = it1, date = it2, category = it4, transactionID = it3) } } } } }
                            transactionViewModel!!.editTransaction(newTransaction!!)

                            Text("Transaction edited successfully!")
                        } else {
                            Text("Amount cannot be empty!")
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            showConfirmation = false

                            if (amount != "") {
                                navController.navigate("dashboard")
                            }
                        }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}


