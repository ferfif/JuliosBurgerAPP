package com.juliosburger.domain.recommendation

import com.juliosburger.domain.core.Result
import kotlinx.coroutines.flow.Flow

interface RecommendationRepository {
    suspend fun save(recommendation: Recommendation): Result<Recommendation>
    fun getByPhone(phoneNumber: String): Flow<List<Recommendation>>
}
