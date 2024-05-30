
package com.example.xtracker.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Query("SELECT * FROM `Transaction` WHERE userID = :userID ORDER BY transactionID DESC;")
    fun getTransactions(userID: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE transactionID = :id")
    fun getTransaction(id: Int): Flow<Transaction>

    @Query("SELECT SUM(amount) as total FROM `Transaction` WHERE type = :type AND userID = :userID")
    fun getTotalForType(type: String, userID: Int): Flow<Double>

}