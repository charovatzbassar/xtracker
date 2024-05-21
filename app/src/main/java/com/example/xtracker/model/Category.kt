package com.example.xtracker.model


import androidx.annotation.DrawableRes

data class Category(
    val id : Int,
    val name : String,
    @DrawableRes var icon : Int?
)


