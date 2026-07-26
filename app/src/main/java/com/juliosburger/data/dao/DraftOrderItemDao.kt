package com.juliosburger.data.dao

import com.juliosburger.data.entity.DraftOrderItemEntity

interface DraftOrderItemDao {
    suspend fun getAll(): List<DraftOrderItemEntity>
    suspend fun getById(id: String): DraftOrderItemEntity?
    suspend fun getByDraftOrder(draftOrderId: String): List<DraftOrderItemEntity>
    suspend fun insert(item: DraftOrderItemEntity)
    suspend fun insertAll(items: List<DraftOrderItemEntity>)
    suspend fun deleteById(id: String)
}
