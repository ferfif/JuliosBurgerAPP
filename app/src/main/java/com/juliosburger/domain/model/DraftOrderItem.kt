package com.juliosburger.domain.model

import java.util.UUID

/**
 * Línea de pedido dentro de un [DraftOrder].
 *
 * Captura el estado del producto y sus modificadores en el momento de la selección.
 *
 * @property id Identificador único inmutable.
 * @property productSnapshot Referencia inmutable al producto tal como existía al momento de la selección.
 * @property quantity Cantidad de unidades del producto.
 * @property modifierSnapshot Lista de modificadores seleccionados en el momento de la agregación.
 * @property notes Observaciones específicas del cliente para esta línea.
 */
data class DraftOrderItem(
    val id: UUID = UUID.randomUUID(),
    val productSnapshot: Product,
    val quantity: Int,
    val modifierSnapshot: List<ModifierOption>,
    val notes: String? = null
)
