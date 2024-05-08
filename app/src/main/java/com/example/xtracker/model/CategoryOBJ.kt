package com.example.xtracker.model

import com.example.xtracker.R

object CategoryOBJ {
    val categories = listOf(
        Category("Income","Paycheck", 200, "2024-01-01", R.drawable.income),
        Category("Expense", "Groceries", 300,"2024-03-01", R.drawable.expenses),
        Category("Saving", "Daily" , 5,"2024-02-01", R.drawable.piggy_bank)
    )
}