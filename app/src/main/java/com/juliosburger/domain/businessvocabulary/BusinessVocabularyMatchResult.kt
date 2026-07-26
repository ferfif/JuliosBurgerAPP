package com.juliosburger.domain.businessvocabulary

import java.util.UUID

/**
 * Resultado de evaluar una frase del cliente contra el vocabulario del negocio.
 *
 * @property matched Indica si se encontró una coincidencia exacta o relevante.
 * @property intent Intención de negocio detectada.
 * @property entities Entidades extraídas de la frase.
 * @property confidence Nivel de confianza de la coincidencia, entre 0.0 y 1.0.
 * @property matchedEntry Entrada del vocabulario que generó la coincidencia.
 */
data class BusinessVocabularyMatchResult(
    val matched: Boolean,
    val intent: BusinessIntent,
    val entities: List<BusinessEntity>,
    val confidence: Double,
    val matchedEntry: BusinessVocabularyEntry? = null
)
