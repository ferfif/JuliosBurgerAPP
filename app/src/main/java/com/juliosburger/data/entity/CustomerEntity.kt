package com.juliosburger.data.entity

data class CustomerEntity(
    val id: String,
    val phone: String,
    val name: String?,
    val favoriteAddress: String?,
    val favoritePaymentMethod: String?,
    val lastPurchaseAt: Long?,
    val purchaseFrequency: Int,
    val confidenceScore: Int
)
