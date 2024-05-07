package com.example.xtracker.model

import androidx.annotation.DrawableRes

data class Type(
    val name: String,
    val total: Int,
    @DrawableRes var image: Int
)
//For the type we mean: INCOME, OUTCOME, EXPENSE
