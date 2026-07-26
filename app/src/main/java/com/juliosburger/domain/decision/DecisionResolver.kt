package com.juliosburger.domain.decision

/**
 * Contrato principal para resolver una decisión conversacional.
 */
interface DecisionResolver {
    suspend fun resolve(request: DecisionRequest): DecisionResult
}
