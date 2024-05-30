
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

    fun getTransactions(userID: Int): Flow<List<Transaction?>> = transactionDao.getTransactions(userID)

    fun getTotalForType(type: String, userID: Int): Flow<Double> = transactionDao.getTotalForType(type, userID)

}