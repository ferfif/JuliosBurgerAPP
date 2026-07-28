package com.juliosburger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juliosburger.data.entity.DraftOrderItemEntity

@Dao
interface DraftOrderItemDao {
    @Query("SELECT * FROM draft_order_items")
    suspend fun getAll(): List<DraftOrderItemEntity>

    @Query("SELECT * FROM draft_order_items WHERE id = :id")
    suspend fun getById(id: String): DraftOrderItemEntity?

    @Query("SELECT * FROM draft_order_items WHERE draftOrderId = :draftOrderId")
    suspend fun getByDraftOrder(draftOrderId: String): List<DraftOrderItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DraftOrderItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DraftOrderItemEntity>)

    @Query("DELETE FROM draft_order_items WHERE id = :id")
    suspend fun deleteById(id: String)
}
