package com.juliosburger.domain.conversation

/**
 * Estados del ciclo de vida conversacional.
 *
 * Cada estado representa una etapa válida dentro del flujo de pedido por WhatsApp.
 * La transición entre estados está regida estrictamente por [ConversationValidator].
 */
enum class ConversationState {
    IDLE,
    WAITING_GREETING,
    WAITING_ORDER,
    WAITING_MODIFIERS,
    WAITING_ADDRESS,
    WAITING_PAYMENT,
    WAITING_CONFIRMATION,
    ORDER_CONFIRMED,
    ORDER_CANCELLED,
    SESSION_EXPIRED
}
