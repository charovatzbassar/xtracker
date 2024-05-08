package com.example.xtracker.model

import androidx.annotation.DrawableRes

data class Category(
    val type: String,
    val name: String,
    val amount: Int,
    val date : String,
    @DrawableRes var image: Int
)
