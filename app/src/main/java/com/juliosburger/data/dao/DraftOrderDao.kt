package com.juliosburger.data.dao

import com.juliosburger.data.entity.DraftOrderEntity

interface DraftOrderDao {
    suspend fun getAll(): List<DraftOrderEntity>
    suspend fun getById(id: String): DraftOrderEntity?
    suspend fun getByStatus(status: String): List<DraftOrderEntity>
    suspend fun insert(order: DraftOrderEntity)
    suspend fun insertAll(orders: List<DraftOrderEntity>)
    suspend fun deleteById(id: String)
}
