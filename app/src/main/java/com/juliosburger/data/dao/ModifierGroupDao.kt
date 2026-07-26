package com.juliosburger.data.dao

import com.juliosburger.data.entity.ModifierGroupEntity

interface ModifierGroupDao {
    suspend fun getAll(): List<ModifierGroupEntity>
    suspend fun getById(id: String): ModifierGroupEntity?
    suspend fun getByProduct(productId: String): List<ModifierGroupEntity>
    suspend fun insert(group: ModifierGroupEntity)
    suspend fun insertAll(groups: List<ModifierGroupEntity>)
    suspend fun deleteById(id: String)
}
