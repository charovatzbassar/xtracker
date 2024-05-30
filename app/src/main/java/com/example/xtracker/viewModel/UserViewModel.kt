package com.example.xtracker.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.xtracker.model.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    var loggedInUserState by mutableStateOf(LoggedInUser())
    private set

    fun isLoggedIn(): Boolean {
        return loggedInUserState == LoggedInUser()
    }

    fun login() {}

    fun register() {}

    fun logout() {
        loggedInUserState = LoggedInUser()
    }
}