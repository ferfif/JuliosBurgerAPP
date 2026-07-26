package com.juliosburger.data.dao

import com.juliosburger.data.entity.CategoryEntity

interface CategoryDao {
    suspend fun getAll(): List<CategoryEntity>
    suspend fun getById(id: String): CategoryEntity?
    suspend fun insert(category: CategoryEntity)
    suspend fun insertAll(categories: List<CategoryEntity>)
    suspend fun deleteById(id: String)
}
