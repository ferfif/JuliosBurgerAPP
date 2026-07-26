package com.juliosburger.domain.customermemory

/**
 * Puntaje de confianza asociado a una memoria o preferencia del cliente.
 */
data class ConfidenceScore(val value: Int) {
    init {
        require(value in 0..100)
    }
}
