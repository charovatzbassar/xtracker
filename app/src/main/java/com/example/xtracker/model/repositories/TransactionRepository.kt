
package com.example.xtracker.model.repositories

import com.example.xtracker.model.TransactionType
import com.example.xtracker.model.dao.TransactionDao
import com.example.xtracker.model.models.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao): BaseRepository<Transaction> {
    override suspend fun insert(t: Transaction) = transactionDao.insert(t)

    override suspend fun update(t: Transaction) = transactionDao.update(t)

    override suspend fun delete(t: Transaction) = transactionDao.delete(t)

    override suspend fun getOneStream(id: Int): Flow<Transaction?> = transactionDao.getTransaction(id)

    fun getTransactions(): Flow<List<Transaction?>> = transactionDao.getTransactions()

    fun getTotal(): Flow<Double> = transactionDao.getTotal()
    fun getTotalForType(type: String): Flow<Double> = transactionDao.getTotalForType(type)
    fun getTotalForCategory(categoryID: Int): Flow<Double> = transactionDao.getTotalForCategory(categoryID)
    fun getTransactionsByType(type: String): Flow<List<Transaction?>> = transactionDao.getTransactionsByType(type)
    fun getTransactionsByCategory(categoryID: Int): Flow<List<Transaction?>> = transactionDao.getTransactionsByCategory(categoryID)
    fun getTotalForCategoryAndType(categoryID: Int, type: String): Flow<Double> = transactionDao.getTotalForCategoryAndType(categoryID, type)
}