package com.example.xtracker.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xtracker.model.repositories.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionRepository: TransactionRepository): ViewModel() {
    var transactionUIState by mutableStateOf(TransactionUIState())
    private set

    init {
        viewModelScope.launch {
            transactionRepository.getTransactions().collect {
                transactions -> transactionUIState.copy(transactions = transactions)
            }
        }
    }
}