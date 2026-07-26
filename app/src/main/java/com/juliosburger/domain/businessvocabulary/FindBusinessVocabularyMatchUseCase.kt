package com.juliosburger.domain.businessvocabulary

/**
 * Busca coincidencias de una frase del cliente en el vocabulario del negocio.
 *
 * @property matcher Estrategia de coincidencia del vocabulario.
 */
class FindBusinessVocabularyMatchUseCase(private val matcher: BusinessVocabularyMatcher) {
    suspend operator fun invoke(message: String): BusinessVocabularyMatchResult? =
        matcher.match(message)
}
