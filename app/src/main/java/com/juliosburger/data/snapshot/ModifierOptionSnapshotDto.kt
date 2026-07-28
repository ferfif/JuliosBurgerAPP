package com.juliosburger.data.snapshot

import kotlinx.serialization.Serializable

@Serializable
data class ModifierOptionSnapshotDto(
    val id: String,
    val modifierGroupId: String,
    val name: String,
    val priceAdjustment: Double,
    val isDefault: Boolean,
    val isActive: Boolean
)
