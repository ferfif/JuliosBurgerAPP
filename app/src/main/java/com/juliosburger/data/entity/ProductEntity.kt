package com.juliosburger.data.entity

data class ProductEntity(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String?,
    val basePrice: Double,
    val imageUrl: String?,
    val isAvailable: Boolean,
    val displayOrder: Int
)
