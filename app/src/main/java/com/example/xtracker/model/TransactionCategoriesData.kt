package com.example.xtracker.model

import com.example.xtracker.model.models.Category

object TransactionCategoriesData {
    val categories = listOf(
        Category(0, TransactionType.INCOME.type),
        Category(1, TransactionType.SAVINGS.type),
        Category(2, TransactionType.EXPENSES.type),
    )
}