
package com.example.xtracker.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.xtracker.model.TransactionType
import com.example.xtracker.model.models.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM `Transaction`")
    suspend fun getTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE transactionID = :id")
    suspend fun getTransaction(id: Int): Flow<Transaction>

    @Query("SELECT * FROM `Transaction` WHERE type = :type")
    suspend fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE categoryID = :categoryID")
    suspend fun getTransactionsByCategory(categoryID: Int): Flow<List<Transaction>>

    @Query("SELECT SUM(amount) as total FROM `Transaction`")
    suspend fun getTotal(): Flow<Int>

    @Query("SELECT SUM(amount) as total FROM `Transaction` WHERE type = :type")
    suspend fun getTotalForType(type: TransactionType): Flow<Int>

    @Query("SELECT SUM(amount) as total FROM `Transaction` WHERE categoryID = :categoryID")
    suspend fun getTotalForCategory(categoryID: Int): Flow<Int>
}