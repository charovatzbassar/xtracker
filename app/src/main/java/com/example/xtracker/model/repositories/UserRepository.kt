package com.example.xtracker.model.repositories

import com.example.xtracker.model.dao.UserDao
import com.example.xtracker.model.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao): BaseRepository<User> {
    override suspend fun insert(t: User) = userDao.insert(t)

    override suspend fun update(t: User) = userDao.update(t)

    override suspend fun delete(t: User) = userDao.delete(t)

    override suspend fun getOneStream(id: Int): Flow<User?> = userDao.getUserByID(id)

    fun getUserByEmail(email: String): Flow<User?> = userDao.getUserByEmail(email)

    fun getUserByUsernameAndPassword(username: String, password: String): Flow<User?> = userDao.getUserByUsernameAndPassword(username, password)
}