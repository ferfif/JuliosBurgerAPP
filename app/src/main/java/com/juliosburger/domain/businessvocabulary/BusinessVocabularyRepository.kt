package com.juliosburger.domain.businessvocabulary

import java.util.UUID
import kotlinx.coroutines.flow.Flow

/**
 * Puerto de salida para acceder y modificar el vocabulario del negocio.
 */
interface BusinessVocabularyRepository {
    fun getAll(): Flow<List<BusinessVocabularyEntry>>
    fun getById(entryId: UUID): Flow<BusinessVocabularyEntry?>
    suspend fun insert(entry: BusinessVocabularyEntry): BusinessVocabularyEntry
    suspend fun update(entry: BusinessVocabularyEntry): BusinessVocabularyEntry
    suspend fun disable(entryId: UUID): BusinessVocabularyEntry
}
