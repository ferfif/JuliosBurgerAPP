package com.juliosburger.domain.decision

/**
 * Resultado de la ejecución de un paso de resolución.
 */
data class DecisionResult(
    val step: DecisionResolutionStep,
    val found: Boolean,
    val confidence: Double,
    val additionalInfo: Map<String, Any> = emptyMap(),
    val shouldContinue: Boolean = false
)
