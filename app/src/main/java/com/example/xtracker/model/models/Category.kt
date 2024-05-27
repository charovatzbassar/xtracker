package com.example.xtracker.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "categoryID")
    val categoryID: Int = 0,

    @ColumnInfo(name = "categoryName")
    val categoryName: String,
)
