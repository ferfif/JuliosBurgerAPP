package com.juliosburger.domain.recommendation

import com.juliosburger.domain.customermemory.CustomerMemory

/**
 * Contrato del motor de generación de recomendaciones.
 */
interface RecommendationEngine {
    suspend fun generateRecommendations(
        customerMemory: CustomerMemory,
        policy: RecommendationPolicy
    ): RecommendationResult
}
