package com.juliosburger.domain.model

import java.util.UUID

/**
 * Representa un producto individual del menú.
 *
 * Pertenece a una [Category] y es la unidad fundamental del flujo de pedido.
 *
 * @property id Identificador único inmutable.
 * @property categoryId Categoría a la que pertenece este producto.
 * @property name Nombre del producto tal como aparece en el menú.
 * @property description Descripción detallada para el cliente.
 * @property basePrice Precio base sin modificadores.
 * @property imageUrl URL de la imagen del producto, si aplica.
 * @property isAvailable Indica si el producto está disponible para la venta.
 * @property displayOrder Orden de aparición dentro de su categoría.
 */
data class Product(
    val id: UUID = UUID.randomUUID(),
    val categoryId: UUID,
    val name: String,
    val description: String? = null,
    val basePrice: Double,
    val imageUrl: String? = null,
    val isAvailable: Boolean = true,
    val displayOrder: Int
)
