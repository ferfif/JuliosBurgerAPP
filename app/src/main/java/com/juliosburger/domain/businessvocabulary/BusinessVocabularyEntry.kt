package com.juliosburger.domain.businessvocabulary

import java.time.Instant
import java.util.UUID

/**
 * Estado operativo de una entrada del vocabulario del negocio.
 */
enum class BusinessVocabularyEntryStatus {
    ACTIVE,
    INACTIVE
}

/**
 * Representa una entrada del vocabulario propio del restaurante.
 *
 * Cada registro mapea una frase coloquial a una intención y entidades
 * conocidas, permitiendo que el sistema entienda el lenguaje del negocio
 * sin depender de inferencia de un proveedor de IA.
 *
 * @property id Identificador único inmutable.
 * @property phrase Patrón de texto reconocido (ej. "con todo").
 * @property intent Intención de negocio asociada.
 * @property entities Entidades extraíbles vinculadas a la frase.
 * @property createdBy Identificador del empleado o cajero que registró la entrada.
 * @property createdAt Timestamp de creación del registro.
 * @property updatedAt Timestamp de la última modificación.
 * @property status Estado actual del registro para soft delete.
 * @property notes Notas operativas contextuales.
 */
data class BusinessVocabularyEntry(
    val id: UUID = UUID.randomUUID(),
    val phrase: String,
    val intent: BusinessIntent,
    val entities: List<String>,
    val createdBy: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
    val status: BusinessVocabularyEntryStatus = BusinessVocabularyEntryStatus.ACTIVE,
    val notes: String? = null
)
