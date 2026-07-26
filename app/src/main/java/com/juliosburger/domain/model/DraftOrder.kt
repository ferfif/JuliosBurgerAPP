package com.juliosburger.domain.model

import java.time.Instant
import java.util.UUID

/**
 * Representa un pedido en etapa de borrador o procesamiento.
 *
 * Es la entidad central del flujo conversacional y operativo.
 * Inicia como borrador, puede ser confirmado por el cliente y posteriormente
 * aceptado por el cajero para avanzar a cocina.
 *
 * @property id Identificador único inmutable del pedido.
 * @property customerPhone Número de WhatsApp del cliente (identificador principal).
 * @property customerName Nombre del cliente, si está disponible.
 * @property deliveryAddress Dirección de entrega, si aplica.
 * @property paymentMethod Método de pago seleccionado.
 * @property status Estado actual dentro del flujo transaccional.
 * @property items Líneas de pedido con sus productos y modificadores.
 * @property cashierNotes Observaciones internas del cajero tras la revisión.
 * @property createdAt Momento de creación del pedido.
 * @property updatedAt Momento de la última actualización.
 */
data class DraftOrder(
    val id: UUID = UUID.randomUUID(),
    val customerPhone: String,
    val customerName: String? = null,
    val deliveryAddress: String? = null,
    val paymentMethod: String? = null,
    val status: DraftOrderStatus,
    val items: List<DraftOrderItem>,
    val cashierNotes: String? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
