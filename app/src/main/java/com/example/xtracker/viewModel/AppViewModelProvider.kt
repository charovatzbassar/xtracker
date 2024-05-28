package com.example.xtracker.viewModel

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.xtracker.MainActivity


object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            TransactionViewModel(
                transactionRepository = MainActivity().container.transactionRepository
            )
        }
    }
}
