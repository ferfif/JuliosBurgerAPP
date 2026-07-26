package com.juliosburger.domain.decision

/**
 * Estrategia individual de resolución dentro del pipeline.
 */
interface DecisionStrategy {
    val step: DecisionResolutionStep
    suspend fun resolve(request: DecisionRequest): DecisionResult
}
