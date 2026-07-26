package com.juliosburger.domain.model

import java.util.UUID

/**
 * Representa una categoría dentro del catálogo del menú.
 *
 * Las categorías organizan los [Product]s en grupos lógicos para la navegación
 * del cliente.
 *
 * @property id Identificador único inmutable.
 * @property name Nombre visible de la categoría.
 * @property description Descripción opcional para el cliente.
 * @property displayOrder Orden de aparición en el menú.
 * @property isActive Indica si la categoría está visible en el menú actual.
 */
data class Category(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String? = null,
    val displayOrder: Int,
    val isActive: Boolean = true
)
