package com.juliosburger.domain.businessvocabulary

import java.util.UUID

/**
 * Desactiva una entrada del vocabulario del negocio.
 *
 * Implementa el soft delete sin eliminar el registro, preservando trazabilidad.
 *
 * @property repository Repositorio de persistencia del vocabulario.
 */
class DisableBusinessVocabularyEntryUseCase(private val repository: BusinessVocabularyRepository) {
    suspend operator fun invoke(entryId: UUID): BusinessVocabularyEntry = repository.disable(entryId)
}
