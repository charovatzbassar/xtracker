package com.example.xtracker.model

data class Transaction(
    val id : Int,
    val amount : Double,
    val category : Category,
    val date : String,
    val type : Type
)