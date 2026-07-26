package com.juliosburger.domain.customermemory

import java.time.Instant
import java.util.UUID

/**
 * Preferencia operativa derivada del historial del cliente.
 */
data class CustomerPreference(
    val category: PreferenceCategory,
    val value: String,
    val confidence: ConfidenceScore,
    val updatedAt: Instant = Instant.now()
)

/**
 * Categorías de preferencia reconocidas por el sistema.
 */
enum class PreferenceCategory {
    PAYMENT_METHOD,
    FAVORITE_PRODUCT,
    MODIFIER,
    DELIVERY_INSTRUCTION
}
