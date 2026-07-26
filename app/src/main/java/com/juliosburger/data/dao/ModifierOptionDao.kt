package com.juliosburger.data.dao

import com.juliosburger.data.entity.ModifierOptionEntity

interface ModifierOptionDao {
    suspend fun getAll(): List<ModifierOptionEntity>
    suspend fun getById(id: String): ModifierOptionEntity?
    suspend fun getByGroup(groupId: String): List<ModifierOptionEntity>
    suspend fun insert(option: ModifierOptionEntity)
    suspend fun insertAll(options: List<ModifierOptionEntity>)
    suspend fun deleteById(id: String)
}
