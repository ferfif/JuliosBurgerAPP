package com.juliosburger.data.entity

data class CategoryEntity(
    val id: String,
    val name: String,
    val description: String?,
    val displayOrder: Int,
    val isActive: Boolean
)
