package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.xtracker.MainActivity
import com.example.xtracker.viewModel.TransactionViewModel


object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            TransactionViewModel(
                transactionRepository = MainActivity().container.transactionRepository
            )
        }

    }
}
