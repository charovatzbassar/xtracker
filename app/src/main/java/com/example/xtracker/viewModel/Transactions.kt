package com.example.xtracker.viewModel

import com.example.xtracker.model.models.Transaction



data class TransactionUIState(
    val transactions: List<TransactionDetails?> = emptyList(),
)

fun TransactionDetails.toTransaction(): Transaction = Transaction(
    transactionID = transactionID,
    amount = amount,
    date = date,
    type = type,
    category = category,
    userID = userID
)

fun Transaction.toTransactionDetails() = TransactionDetails(
    transactionID = transactionID,
    amount = amount,
    date = date,
    type = type,
    category = category,
    userID = userID
)