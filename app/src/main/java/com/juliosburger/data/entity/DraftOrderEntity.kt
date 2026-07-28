package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "draft_orders",
    indices = [Index(value = ["status"])]
)
data class DraftOrderEntity(
    @PrimaryKey val id: String,
    val customerPhone: String,
    val customerName: String?,
    val deliveryAddress: String?,
    val paymentMethod: String?,
    val status: String,
    val cashierNotes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
