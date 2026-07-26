package com.juliosburger.domain.recommendation

/**
 * Razón por la que se genera una recomendación.
 */
enum class RecommendationReason {
    FREQUENT_PURCHASE,
    LAST_ORDER,
    CUSTOMER_PREFERENCE,
    HIGH_CONFIDENCE_PATTERN
}
