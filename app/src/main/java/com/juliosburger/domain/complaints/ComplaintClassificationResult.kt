package com.juliosburger.domain.complaints

data class ComplaintClassificationResult(
    val category: ComplaintCategory,
    val confidence: Double,
    val observations: String? = null
)
