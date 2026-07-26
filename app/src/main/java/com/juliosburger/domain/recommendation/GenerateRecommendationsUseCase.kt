package com.juliosburger.domain.recommendation

import com.juliosburger.domain.customermemory.ConfidenceScore
import com.juliosburger.domain.customermemory.CustomerMemory

class GenerateRecommendationsUseCase(
    private val engine: RecommendationEngine,
    private val policy: RecommendationPolicy = RecommendationPolicy()
) {
    suspend operator fun invoke(customerMemory: CustomerMemory): RecommendationResult {
        if (customerMemory.confidenceScore.value < policy.minimumConfidenceScore) {
            return RecommendationResult(
                suggestionAllowed = false,
                reason = "Confidence score is below minimum threshold"
            )
        }

        if (customerMemory.favoriteProductIds.isEmpty()) {
            return RecommendationResult(
                suggestionAllowed = false,
                reason = "No frequent products available"
            )
        }

        return engine.generateRecommendations(customerMemory, policy)
    }
}
