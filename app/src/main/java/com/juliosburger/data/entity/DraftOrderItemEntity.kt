package com.juliosburger.data.entity

data class DraftOrderItemEntity(
    val id: String,
    val draftOrderId: String,
    val productSnapshot: String,
    val quantity: Int,
    val modifierSnapshot: String,
    val notes: String?
)
