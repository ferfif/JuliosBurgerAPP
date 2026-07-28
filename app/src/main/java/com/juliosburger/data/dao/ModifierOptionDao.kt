package com.juliosburger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juliosburger.data.entity.ModifierOptionEntity

@Dao
interface ModifierOptionDao {
    @Query("SELECT * FROM modifier_options")
    suspend fun getAll(): List<ModifierOptionEntity>

    @Query("SELECT * FROM modifier_options WHERE id = :id")
    suspend fun getById(id: String): ModifierOptionEntity?

    @Query("SELECT * FROM modifier_options WHERE modifierGroupId = :groupId")
    suspend fun getByGroup(groupId: String): List<ModifierOptionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(option: ModifierOptionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(options: List<ModifierOptionEntity>)

    @Query("DELETE FROM modifier_options WHERE id = :id")
    suspend fun deleteById(id: String)
}
