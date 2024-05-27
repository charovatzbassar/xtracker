package com.example.xtracker.viewModel

import com.example.xtracker.model.models.Transaction
import com.example.xtracker.model.TransactionType
import com.example.xtracker.viewModel.TransactionUIState
import com.example.xtracker.viewModel.TransactionDetails

data class CategoryDetails(
    val categoryID: Int = 0,
    val categoryName: String = ""
)

data class TransactionDetails(
    val transactionID: Int = 0,
    val amount: Double = 0.0,
    val date: String = "",
    val type: String = "",
    val categoryID: Int = 0
)

data class TransactionUIState(
    val transactions: List<TransactionDetails> = emptyList()
)

fun TransactionDetails.toTransaction(): Transaction = Transaction(
    transactionID = transactionID,
    amount = amount,
    date = date,
    type = type,
    categoryID = categoryID
)

fun Transaction.toTransactionDetails() = TransactionDetails(
    transactionID = transactionID,
    amount = amount,
    date = date,
    type = type,
    categoryID = categoryID
)

fun Transaction.toDashboardUIState(): TransactionUIState = TransactionUIState(
    transactions = listOf(this.toTransactionDetails())
)

// Assuming Transaction is a data class defined somewhere in your codebase
data class Transaction(
    val transactionID: Int,
    val amount: Double,
    val date: String,
    val type: String,
    val categoryID: Int
)