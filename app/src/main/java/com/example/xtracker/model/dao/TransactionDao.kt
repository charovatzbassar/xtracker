
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
    fun getTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE transactionID = :id")
    fun getTransaction(id: Int): Flow<Transaction>

    @Query("SELECT * FROM `Transaction` WHERE type = :type")
    fun getTransactionsByType(type: String): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE category = :category")
    fun getTransactionsByCategory(category: String): Flow<List<Transaction>>

    @Query("SELECT SUM(amount) as total FROM `Transaction`")
    fun getTotal(): Flow<Double>

    @Query("SELECT SUM(amount) as total FROM `Transaction` WHERE type = :type")
    fun getTotalForType(type: String): Flow<Double>

    @Query("SELECT SUM(amount) as total FROM `Transaction` WHERE category = :category")
    fun getTotalForCategory(category: String): Flow<Double>

    @Query("SELECT SUM(amount) as total FROM `Transaction` WHERE category = :category AND type = :type")
    fun getTotalForCategoryAndType(category: String, type: String): Flow<Double>
}