package com.example.xtracker.viewModel

import com.example.xtracker.model.models.User

fun UserDetails.toUser(): User = User(
    userID = userID,
    username = username,
    email = email,
    password = password
)

fun User.toUserDetails() = UserDetails(
    userID = userID,
    username = username,
    email = email,
    password = password
)