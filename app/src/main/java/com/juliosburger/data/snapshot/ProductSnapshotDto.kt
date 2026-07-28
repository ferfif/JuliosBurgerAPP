package com.juliosburger.data.snapshot

import kotlinx.serialization.Serializable

@Serializable
data class ProductSnapshotDto(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String?,
    val basePrice: Double,
    val imageUrl: String?,
    val isAvailable: Boolean,
    val displayOrder: Int
)
