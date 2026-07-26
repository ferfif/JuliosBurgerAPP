package com.juliosburger.data.entity

data class ModifierGroupEntity(
    val id: String,
    val productId: String,
    val name: String,
    val minSelection: Int,
    val maxSelection: Int,
    val isRequired: Boolean,
    val displayOrder: Int
)
