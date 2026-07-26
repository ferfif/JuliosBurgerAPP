package com.juliosburger.domain.customermemory

import java.time.Instant
import java.util.UUID

/**
 * Memoria histórica del cliente utilizada para personalizar la experiencia conversacional.
 *
 * Nunca modifica pedidos de forma automática. Su uso es estrictamente propositivo
 * y sujeto a políticas de confianza.
 */
data class CustomerMemory(
    val phone: String,
    val name: String? = null,
    val addresses: List<CustomerAddress> = emptyList(),
    val preferences: List<CustomerPreference> = emptyList(),
    val favoriteProductIds: List<UUID> = emptyList(),
    val lastPurchaseAt: Instant? = null,
    val purchaseFrequency: Int = 0,
    val confidenceScore: ConfidenceScore = ConfidenceScore(0),
    val lastUpdated: Instant = Instant.now()
)
