package com.juliosburger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juliosburger.data.entity.DraftOrderEntity

@Dao
interface DraftOrderDao {
    @Query("SELECT * FROM draft_orders")
    suspend fun getAll(): List<DraftOrderEntity>

    @Query("SELECT * FROM draft_orders WHERE id = :id")
    suspend fun getById(id: String): DraftOrderEntity?

    @Query("SELECT * FROM draft_orders WHERE status = :status")
    suspend fun getByStatus(status: String): List<DraftOrderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: DraftOrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(orders: List<DraftOrderEntity>)

    @Query("DELETE FROM draft_orders WHERE id = :id")
    suspend fun deleteById(id: String)
}
