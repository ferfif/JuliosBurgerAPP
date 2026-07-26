package com.juliosburger.domain.businessvocabulary

/**
 * Contrato del motor de coincidencia del vocabulario del negocio.
 *
 * Su implementación determina cómo una frase del cliente se transforma
 * en una intención y entidades reconocidas sin invocar proveedores de IA externos.
 */
interface BusinessVocabularyMatcher {
    suspend fun match(message: String): BusinessVocabularyMatchResult?
}
