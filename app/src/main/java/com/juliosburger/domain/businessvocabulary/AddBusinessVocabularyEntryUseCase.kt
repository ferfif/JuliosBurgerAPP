package com.juliosburger.domain.businessvocabulary

/**
 * Crea una nueva entrada en el vocabulario del negocio.
 *
 * @property repository Repositorio de persistencia del vocabulario.
 */
class AddBusinessVocabularyEntryUseCase(private val repository: BusinessVocabularyRepository) {
    suspend operator fun invoke(entry: BusinessVocabularyEntry): BusinessVocabularyEntry =
        repository.insert(entry)
}
