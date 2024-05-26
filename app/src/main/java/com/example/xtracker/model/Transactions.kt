package com.example.xtracker.model

object Transactions {
    val transactions = listOf(
        Transaction(1, 50.0, foodCategory, "2024-05-01", Type.EXPENSE),
        Transaction(2, 100.0, entertainmentCategory, "2024-05-02", Type.EXPENSE),
        Transaction(3, 2000.0, utilitiesCategory, "2024-05-03", Type.EXPENSE),
        Transaction(4, -1.0, otherCategory, "2024-05-04", Type.INCOME),
        Transaction(5, 75.0, transportCategory, "2024-05-05", Type.EXPENSE),
        Transaction(6, 150.0, otherCategory, "2024-05-06", Type.EXPENSE),
        Transaction(7, -300.0, foodCategory, "2024-05-07", Type.EXPENSE),
        Transaction(8, 4500.0, otherCategory, "2024-05-08", Type.INCOME),
        Transaction(9, 60.0, transportCategory, "2024-05-09", Type.EXPENSE),
        Transaction(10, 200.0, entertainmentCategory, "2024-05-10", Type.EXPENSE),
        // Savings Transactions (we rich)
        Transaction(11, 5000.0, savingsCategory, "2024-05-11", Type.SAVING),
        Transaction(12, 300.0, savingsCategory, "2024-05-12", Type.SAVING)
        Transaction(10, -100.0, entertainmentCategory, "2024-05-10", Type.EXPENSE)
    )
}

// Sample categories
val foodCategory = Category(1, "Food", null)
val transportCategory = Category(2, "Transport", null)
val entertainmentCategory = Category(3, "Entertainment", null)
val utilitiesCategory = Category(4, "Utilities", null)
val otherCategory = Category(5, "Other", null)
val savingsCategory = Category(6, "Savings", null) // New Category for saving watch out while handling backend :)
