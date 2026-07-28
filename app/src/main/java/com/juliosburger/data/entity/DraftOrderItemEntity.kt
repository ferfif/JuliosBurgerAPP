package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "draft_order_items",
    indices = [Index(value = ["draftOrderId"])],
    foreignKeys = [ForeignKey(
        entity = DraftOrderEntity::class,
        parentColumns = ["id"],
        childColumns = ["draftOrderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DraftOrderItemEntity(
    @PrimaryKey val id: String,
    val draftOrderId: String,
    val productSnapshot: String,
    val quantity: Int,
    val modifierSnapshot: String,
    val notes: String?
)
