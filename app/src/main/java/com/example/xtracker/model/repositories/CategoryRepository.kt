package com.example.xtracker.model.repositories

import com.example.xtracker.model.dao.CategoryDao
import com.example.xtracker.model.models.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao): BaseRepository<Category> {
    override suspend fun insert(t: Category) = categoryDao.insert(t)

    override suspend fun update(t: Category) = categoryDao.update(t)

    override suspend fun delete(t: Category) = categoryDao.delete(t)

    override suspend fun getOneStream(id: Int): Flow<Category?> = categoryDao.getCategory(id)

    suspend fun getCourses(): Flow<List<Category?>> = categoryDao.getCategories()
}