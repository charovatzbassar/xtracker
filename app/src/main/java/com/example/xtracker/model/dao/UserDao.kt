
package com.example.xtracker.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.xtracker.model.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM User;")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE userID = :id")
    fun getUserByID(id: Int): Flow<User>

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUserByEmail(email: String): Flow<User>
}