package com.juliosburger.domain.conversation

/**
 * Representa una transición válida entre estados conversacionales.
 *
 * Permite verificar que un evento aplica en un estado origen
 * y conduce a un estado destino permitido.
 */
data class ConversationTransition(
    val fromState: ConversationState,
    val event: ConversationEvent,
    val toState: ConversationState
) {
    fun isValidFor(state: ConversationState, event: ConversationEvent): Boolean =
        state == fromState && this.event == event
}
