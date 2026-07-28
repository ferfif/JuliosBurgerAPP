package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val displayOrder: Int,
    val isActive: Boolean
)
