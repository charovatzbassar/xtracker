package com.example.xtracker.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xtracker.model.TransactionType
import com.example.xtracker.model.models.Transaction
import com.example.xtracker.model.repositories.TransactionRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionRepository: TransactionRepository): ViewModel() {
    var transactionUIState by mutableStateOf(TransactionUIState())
    private set
    var totalIncomeState by mutableDoubleStateOf(0.0)
    private set
    var totalExpenseState by mutableDoubleStateOf(0.0)
    private set
    var totalSavingState by mutableDoubleStateOf(0.0)
    private set

    var currentTransaction: Transaction? by mutableStateOf(null)
    private set

    init {
        viewModelScope.launch {
            transactionRepository.getTransactions().collect {
                transactions ->
                transactionUIState = transactionUIState.copy(
                    transactions = transactions.map { it?.toTransactionDetails() }
                )
            }
        }
    }

    fun getTotalForType(type: String) {
        viewModelScope.launch {
            when (type) {
                "Expenses" -> transactionRepository.getTotalForType(TransactionType.EXPENSES.type).collect {
                        totalExpenses -> totalExpenseState = totalExpenses
                }
                "Savings" -> transactionRepository.getTotalForType(TransactionType.SAVINGS.type).collect {
                        totalSavings -> totalSavingState = totalSavings
                }
                "Income" -> transactionRepository.getTotalForType(TransactionType.INCOME.type).collect {
                        totalIncome -> totalIncomeState = totalIncome
                }
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.insert(transaction)
        }
    }

    fun editTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.update(transaction)
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.delete(transaction)
        }
    }

    fun getTransactionById(id: Int) {
        viewModelScope.launch {
            currentTransaction = transactionRepository.getOneStream(id).firstOrNull()
        }
    }
}