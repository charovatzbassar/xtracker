package com.example.xtracker.model

import android.content.Context
import com.example.xtracker.model.repositories.TransactionRepository
import com.example.xtracker.model.repositories.UserRepository


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