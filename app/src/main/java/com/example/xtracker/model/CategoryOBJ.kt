package com.example.xtracker.model

import com.example.xtracker.R

object CategoryOBJ {
    val categories = listOf(
        Category("Income", 200, "2024-01-01", R.drawable.income),
        Category("Expense", 300,"2024-03-01", R.drawable.expenses),
        Category("Saving", 400,"2024-02-01", R.drawable.piggy_bank)
    )
}