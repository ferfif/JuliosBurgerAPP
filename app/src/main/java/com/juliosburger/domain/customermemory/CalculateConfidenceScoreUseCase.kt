package com.juliosburger.domain.customermemory

/**
 * Calcula el puntaje de confianza de una memoria de cliente.
 *
 * Utiliza una heurística básica basada en frecuencia y consistencia.
 * El algoritmo puede evolucionar sin alterar el contrato del dominio.
 */
class CalculateConfidenceScoreUseCase {
    suspend operator fun invoke(
        purchaseFrequency: Int,
        consecutiveMatches: Int,
        totalInteractions: Int
    ): ConfidenceScore {
        if (totalInteractions == 0) return ConfidenceScore(0)

        val frequencyFactor = (purchaseFrequency.coerceAtMost(20) / 20.0)
        val consistencyFactor = (consecutiveMatches.toDouble() / totalInteractions.coerceAtLeast(1))
        val score = ((frequencyFactor * 0.4 + consistencyFactor * 0.6) * 100).toInt().coerceIn(0, 100)

        return ConfidenceScore(score)
    }
}
