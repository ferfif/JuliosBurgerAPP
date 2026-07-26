package com.juliosburger.domain.model

import java.time.Instant
import java.util.UUID

/**
 * Representa la memoria persistente de un cliente.
 *
 * Almacena información histórica exclusivamente para personalizar
 * la experiencia conversacional. Nunca altera pedidos de forma
 * automática sin intervención humana.
 *
 * @property id Identificador único inmutable.
 * @property phone Número de teléfono (identificador principal para WhatsApp).
 * @property name Nombre registrado del cliente.
 * @property favoriteAddress Dirección de entrega preferida.
 * @property favoritePaymentMethod Método de pago habitual.
 * @property lastPurchaseAt Fecha de la última compra registrada.
 * @property purchaseFrequency Número total de compras realizadas.
 * @property confidenceScore Puntaje de confianza (0-100) que mide la fiabilidad
 * de las preferencias almacenadas.
 */
data class Customer(
    val id: UUID = UUID.randomUUID(),
    val phone: String,
    val name: String? = null,
    val favoriteAddress: String? = null,
    val favoritePaymentMethod: String? = null,
    val lastPurchaseAt: Instant? = null,
    val purchaseFrequency: Int = 0,
    val confidenceScore: Int = 0
)
