package com.example.xtracker.model.repositories

import kotlinx.coroutines.flow.Flow


interface BaseRepository<T> {
    suspend fun insert(t: T)

    suspend fun update(t: T)

    suspend fun delete(t: T)

    suspend fun getOneStream(id: Int): Flow<T?>
}