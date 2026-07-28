package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "modifier_groups",
    indices = [Index(value = ["productId"])],
    foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = ["id"],
        childColumns = ["productId"],
        onDelete = ForeignKey.RESTRICT
    )]
)
data class ModifierGroupEntity(
    @PrimaryKey val id: String,
    val productId: String,
    val name: String,
    val minSelection: Int,
    val maxSelection: Int,
    val isRequired: Boolean,
    val displayOrder: Int
)
