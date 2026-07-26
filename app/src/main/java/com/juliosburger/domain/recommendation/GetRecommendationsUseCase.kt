package com.juliosburger.domain.recommendation

import kotlinx.coroutines.flow.Flow

class GetRecommendationsUseCase(private val repository: RecommendationRepository) {
    operator fun invoke(phoneNumber: String): Flow<List<Recommendation>> =
        repository.getByPhone(phoneNumber)
}
