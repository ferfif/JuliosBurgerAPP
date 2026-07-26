package com.juliosburger.data.entity

data class DraftOrderEntity(
    val id: String,
    val customerPhone: String,
    val customerName: String?,
    val deliveryAddress: String?,
    val paymentMethod: String?,
    val status: String,
    val cashierNotes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
