package com.juliosburger.domain.conversation

/**
 * Acciones que el Conversation Engine indica al sistema externo
 * tras procesar un evento.
 */
sealed class ConversationAction {
    object WaitForInput : ConversationAction()
    object NotifyCashier : ConversationAction()
    object CompleteOrder : ConversationAction()
    object CancelSession : ConversationAction()
    data class RequestInformation(val field: String) : ConversationAction()
}

/**
 * Resultado del procesamiento de un evento conversacional.
 */
data class ConversationResult(
    val nextState: ConversationState,
    val updatedContext: ConversationContext,
    val nextAction: ConversationAction,
    val validationErrors: List<String> = emptyList()
)
