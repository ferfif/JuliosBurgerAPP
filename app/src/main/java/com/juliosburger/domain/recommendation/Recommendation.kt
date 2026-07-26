package com.juliosburger.domain.recommendation

import com.juliosburger.domain.core.Identifier
import com.juliosburger.domain.customermemory.ConfidenceScore
import com.juliosburger.domain.model.Product
import java.time.Instant

/**
 * Entidad inmutable que representa una sugerencia de producto para un cliente.
 */
data class Recommendation(
    val id: Identifier = Identifier.generate(),
    val phoneNumber: String,
    val product: Product,
    val confidenceScore: ConfidenceScore,
    val reason: RecommendationReason,
    val createdAt: Instant = Instant.now()
)
