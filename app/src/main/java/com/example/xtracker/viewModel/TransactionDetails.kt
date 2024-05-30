package com.example.xtracker.viewModel

data class TransactionDetails(
    val transactionID: Int = 0,
    val amount: Double = 0.0,
    val date: String = "",
    val type: String = "",
    val category: String = "",
    val userID: Int = 0
)
