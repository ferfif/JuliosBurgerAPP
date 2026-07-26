package com.juliosburger.domain.customermemory

/**
 * Políticas de uso seguro de la memoria del cliente.
 *
 * Centraliza reglas inmutables que garantizan que la memoria nunca altere pedidos
 * de forma automática y solo se utilice cuando la confianza supera el umbral mínimo.
 */
object CustomerMemoryPolicy {
    const val MIN_CONFIDENCE_FOR_SUGGESTION = 70
    const val MAX_PREFERENCES_PER_CATEGORY = 10

    fun isRecommendationAllowed(score: ConfidenceScore): Boolean = score.value >= MIN_CONFIDENCE_FOR_SUGGESTION
}
