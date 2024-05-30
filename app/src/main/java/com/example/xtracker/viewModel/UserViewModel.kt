package com.example.xtracker.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.xtracker.model.models.User
import com.example.xtracker.model.repositories.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val userRepository: UserRepository, private val navController: NavHostController, private val isLoggedIn: MutableState<Boolean>, private val userState: MutableState<UserDetails?>): ViewModel() {

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userRepository.getUserByUsernameAndPassword(username, password).collect { user ->
                if (user != null) {
                    userState.value = user.toUserDetails()
                    isLoggedIn.value = true
                    navController.navigate("dashboard")
                }
            }


        }
    }

    fun register(user: UserDetails) {
        viewModelScope.launch {
            val newUser = user.toUser()
            userRepository.getUserByEmail(newUser.email).collect { user ->
                if (user == null) {
                    userRepository.insert(newUser)
                    login(newUser.username, newUser.password)
                }
            }
        }
    }

    fun logout() {
        userState.value = UserDetails()
        isLoggedIn.value = false
    }
}