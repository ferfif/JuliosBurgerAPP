package com.juliosburger.domain.recommendation

/**
 * Resultado de la generación de recomendaciones.
 */
data class RecommendationResult(
    val recommendations: List<Recommendation> = emptyList(),
    val suggestionAllowed: Boolean = false,
    val reason: String? = null
)
