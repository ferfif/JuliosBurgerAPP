package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    indices = [Index(value = ["categoryId"])],
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.RESTRICT
    )]
)
data class ProductEntity(
    @PrimaryKey val id: String,
    val categoryId: String,
    val name: String,
    val description: String?,
    val basePrice: Double,
    val imageUrl: String?,
    val isAvailable: Boolean,
    val displayOrder: Int
)
