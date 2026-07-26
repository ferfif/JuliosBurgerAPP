package com.juliosburger.domain.complaints

class ClassifyComplaintUseCase(private val classifier: ComplaintClassifier) {
    suspend operator fun invoke(description: String): ComplaintClassificationResult {
        return classifier.classify(description)
    }
}
