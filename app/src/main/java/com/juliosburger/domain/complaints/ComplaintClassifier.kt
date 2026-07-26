package com.juliosburger.domain.complaints

interface ComplaintClassifier {
    suspend fun classify(description: String): ComplaintClassificationResult
}
