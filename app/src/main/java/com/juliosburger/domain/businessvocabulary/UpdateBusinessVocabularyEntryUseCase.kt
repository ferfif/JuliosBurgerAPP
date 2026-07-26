package com.juliosburger.domain.businessvocabulary

/**
 * Actualiza una entrada existente del vocabulario del negocio.
 *
 * @property repository Repositorio de persistencia del vocabulario.
 */
class UpdateBusinessVocabularyEntryUseCase(private val repository: BusinessVocabularyRepository) {
    suspend operator fun invoke(entry: BusinessVocabularyEntry): BusinessVocabularyEntry =
        repository.update(entry)
}
