package com.juliosburger.data.snapshot

import kotlinx.serialization.Serializable

@Serializable
data class DraftOrderItemSnapshotDto(
    val product: ProductSnapshotDto,
    val modifiers: List<ModifierOptionSnapshotDto>
)
