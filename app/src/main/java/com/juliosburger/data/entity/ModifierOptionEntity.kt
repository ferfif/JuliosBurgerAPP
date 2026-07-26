package com.juliosburger.data.entity

data class ModifierOptionEntity(
    val id: String,
    val modifierGroupId: String,
    val name: String,
    val priceAdjustment: Double,
    val isDefault: Boolean,
    val isActive: Boolean
)
