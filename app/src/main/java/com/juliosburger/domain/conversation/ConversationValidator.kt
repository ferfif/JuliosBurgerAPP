package com.juliosburger.domain.conversation

/**
 * Centraliza todas las reglas de validación para transiciones conversacionales.
 *
 * Garantiza que el motor de conversación nunca aplique transiciones
 * que violen las reglas del negocio definidas en el documento de arquitectura.
 */
object ConversationValidator {
    fun validate(
        currentState: ConversationState,
        event: ConversationEvent,
        context: ConversationContext
    ): List<String> = buildList {
        when (currentState) {
            ConversationState.WAITING_CONFIRMATION -> {
                when (event) {
                    is ConversationEvent.ConfirmationReceived -> {
                        if (context.draftOrder?.deliveryAddress.isNullOrBlank()) {
                            add("No se puede confirmar un pedido sin dirección de entrega.")
                        }
                    }
                    else -> Unit
                }
            }
            ConversationState.ORDER_CONFIRMED -> {
                add("El pedido ya fue confirmado. No se pueden realizar más modificaciones.")
            }
            ConversationState.ORDER_CANCELLED -> {
                add("El pedido fue cancelado. Inicie una nueva conversación.")
            }
            ConversationState.SESSION_EXPIRED -> {
                add("La sesión expiró. Inicie una nueva conversación.")
            }
            else -> Unit
        }
    }
}
