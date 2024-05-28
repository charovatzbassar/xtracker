package com.example.xtracker.model

import android.content.Context
import com.example.xtracker.model.repositories.TransactionRepository


interface AppContainer {
    val transactionRepository: TransactionRepository
}
class AppDataContainer(private val context: Context): AppContainer{

    override val transactionRepository: TransactionRepository by lazy {
        TransactionRepository(XTrackerDatabase.getDatabase(context).transactionDao())
    }

}