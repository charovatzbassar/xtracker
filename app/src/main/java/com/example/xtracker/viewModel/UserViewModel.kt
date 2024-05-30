package com.example.xtracker.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xtracker.model.models.User
import com.example.xtracker.model.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    var userDetailsState by mutableStateOf(UserDetails())
    private set

    fun isLoggedIn(): Boolean {
        return userDetailsState != UserDetails()
    }

    fun login(user: UserDetails) {
        viewModelScope.launch {
            val newUser = user.toUser()
            var foundUser: User? = null
            userRepository.getUserByEmail(newUser.email).collect { user ->
                foundUser = user
            }

            if (foundUser != null) {
                userDetailsState = newUser.toUserDetails()
            }
        }
    }

    fun register(user: UserDetails) {
        viewModelScope.launch {
            val newUser = user.toUser()
            var foundUser: User? = null
            userRepository.getUserByEmail(newUser.email).collect { user ->
                foundUser = user
            }

            if (foundUser == null) {
                userRepository.insert(newUser)
                userDetailsState = newUser.toUserDetails()
            }
        }
    }

    fun logout() {
        userDetailsState = UserDetails()
    }
}