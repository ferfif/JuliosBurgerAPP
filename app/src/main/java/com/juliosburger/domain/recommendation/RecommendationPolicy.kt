package com.juliosburger.domain.recommendation

/**
 * Políticas de generación de recomendaciones.
 */
data class RecommendationPolicy(
    val minimumConfidenceScore: Int = 70,
    val maximumRecommendations: Int = 3
)
