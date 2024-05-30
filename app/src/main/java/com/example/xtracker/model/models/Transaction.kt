package com.example.xtracker.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.xtracker.model.TransactionType
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Transaction", foreignKeys = [
    ForeignKey(entity = User::class, childColumns = ["userID"], parentColumns = ["userID"])
])
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "transactionID")
    val transactionID: Int = 0,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "userID")
    val userID: Int
)
