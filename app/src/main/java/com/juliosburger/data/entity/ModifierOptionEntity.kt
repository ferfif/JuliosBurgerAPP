package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "modifier_options",
    indices = [Index(value = ["modifierGroupId"])],
    foreignKeys = [ForeignKey(
        entity = ModifierGroupEntity::class,
        parentColumns = ["id"],
        childColumns = ["modifierGroupId"],
        onDelete = ForeignKey.RESTRICT
    )]
)
data class ModifierOptionEntity(
    @PrimaryKey val id: String,
    val modifierGroupId: String,
    val name: String,
    val priceAdjustment: Double,
    val isDefault: Boolean,
    val isActive: Boolean
)
