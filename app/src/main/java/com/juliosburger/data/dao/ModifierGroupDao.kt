package com.juliosburger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juliosburger.data.entity.ModifierGroupEntity

@Dao
interface ModifierGroupDao {
    @Query("SELECT * FROM modifier_groups")
    suspend fun getAll(): List<ModifierGroupEntity>

    @Query("SELECT * FROM modifier_groups WHERE id = :id")
    suspend fun getById(id: String): ModifierGroupEntity?

    @Query("SELECT * FROM modifier_groups WHERE productId = :productId")
    suspend fun getByProduct(productId: String): List<ModifierGroupEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: ModifierGroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groups: List<ModifierGroupEntity>)

    @Query("DELETE FROM modifier_groups WHERE id = :id")
    suspend fun deleteById(id: String)
}
