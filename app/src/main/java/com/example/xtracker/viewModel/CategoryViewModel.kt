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
import com.example.xtracker.model.models.Category
import com.example.xtracker.model.models.Transaction
import com.example.xtracker.model.repositories.CategoryRepository
import com.example.xtracker.model.repositories.TransactionRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository): ViewModel() {
    var categories: List<Category?> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            categoryRepository.getCategories().collect {
                fetchedCategories -> categories = fetchedCategories
            }
        }
    }
}