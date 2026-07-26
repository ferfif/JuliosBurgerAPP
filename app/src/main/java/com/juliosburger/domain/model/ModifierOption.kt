package com.juliosburger.domain.model

import java.util.UUID

/**
 * Opción concreta dentro de un [ModifierGroup].
 *
 * Representa una elección individual con posible ajuste de precio.
 *
 * @property id Identificador único inmutable.
 * @property modifierGroupId Grupo al que pertenece esta opción.
 * @property name Nombre visible de la opción.
 * @property priceAdjustment Incremento o decremento sobre el precio base.
 * @property isDefault Indica si esta opción se selecciona automáticamente.
 * @property isActive Indica si la opción está disponible actualmente.
 */
data class ModifierOption(
    val id: UUID = UUID.randomUUID(),
    val modifierGroupId: UUID,
    val name: String,
    val priceAdjustment: Double = 0.0,
    val isDefault: Boolean = false,
    val isActive: Boolean = true
)
