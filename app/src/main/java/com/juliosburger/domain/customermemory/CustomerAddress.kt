package com.juliosburger.domain.customermemory

import java.time.Instant

/**
 * Dirección registrada de un cliente.
 */
data class CustomerAddress(
    val address: String,
    val isFavorite: Boolean = false,
    val usageCount: Int = 0,
    val lastUsedAt: Instant? = null
)
