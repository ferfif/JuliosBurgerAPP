package com.juliosburger.domain.model

import java.util.UUID

/**
 * Agrupa opciones de personalización para un [Product].
 *
 * Controla las reglas de selección aplicables a un producto
 * (ej. "Ingredientes adicionales", "Tipo de pan").
 *
 * @property id Identificador único inmutable.
 * @property productId Producto al que pertenece este grupo.
 * @property name Nombre del grupo visible para el cliente.
 * @property minSelection Cantidad mínima de opciones que el cliente debe seleccionar.
 * @property maxSelection Cantidad máxima de opciones seleccionables.
 * @property isRequired Indica si este grupo es obligatorio en el pedido.
 * @property displayOrder Orden de visualización en la UI.
 */
data class ModifierGroup(
    val id: UUID = UUID.randomUUID(),
    val productId: UUID,
    val name: String,
    val minSelection: Int,
    val maxSelection: Int,
    val isRequired: Boolean,
    val displayOrder: Int
)
