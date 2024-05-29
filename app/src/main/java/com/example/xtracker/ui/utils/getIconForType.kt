package com.example.xtracker.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.xtracker.model.TransactionType

fun getIconForType(title: String): ImageVector {
    return when (title) {
        TransactionType.INCOME.type -> Icons.Default.AddCircle
        TransactionType.EXPENSES.type -> Icons.Default.ShoppingCart
        TransactionType.SAVINGS.type -> Icons.Default.Email
        else -> Icons.Default.AddCircle
    }
}