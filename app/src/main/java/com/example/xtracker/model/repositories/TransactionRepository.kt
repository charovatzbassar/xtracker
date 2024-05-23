
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

    suspend fun getTransactions(): Flow<List<Transaction>> = transactionDao.getTransactions()

    suspend fun getTotal(): Flow<Int> = transactionDao.getTotal()
    suspend fun getTotalForType(type: TransactionType): Flow<Int> = transactionDao.getTotalForType(type)
    suspend fun getTotalForCategory(categoryID: Int): Flow<Int> = transactionDao.getTotalForCategory(categoryID)
    suspend fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>> = transactionDao.getTransactionsByType(type)
    suspend fun getTransactionsByCategory(categoryID: Int): Flow<List<Transaction>> = transactionDao.getTransactionsByCategory(categoryID)
}