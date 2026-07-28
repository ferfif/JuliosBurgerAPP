package com.juliosburger.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customers",
    indices = [Index(value = ["phone"], unique = true)]
)
data class CustomerEntity(
    @PrimaryKey val id: String,
    val phone: String,
    val name: String?,
    val favoriteAddress: String?,
    val favoritePaymentMethod: String?,
    val lastPurchaseAt: Long?,
    val purchaseFrequency: Int,
    val confidenceScore: Int
)
