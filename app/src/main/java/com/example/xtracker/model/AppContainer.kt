package com.example.xtracker.model

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.xtracker.model.repositories.TransactionRepository
import com.example.xtracker.model.repositories.UserRepository
import com.example.xtracker.viewModel.UserDetails


interface AppContainer {
    val transactionRepository: TransactionRepository
    val userRepository: UserRepository
}
class AppDataContainer(private val context: Context): AppContainer{

    override val transactionRepository: TransactionRepository by lazy {
        TransactionRepository(XTrackerDatabase.getDatabase(context).transactionDao())
    }

    override val userRepository: UserRepository by lazy {
        UserRepository(XTrackerDatabase.getDatabase(context).userDao())
    }


}