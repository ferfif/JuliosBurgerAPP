package com.juliosburger.domain.conversation

/**
 * Núcleo del motor conversacional.
 *
 * Mantiene la máquina de estados, valida transiciones y actualiza el contexto
 * sin depender de frameworks ni infraestructura externa.
 */
class ConversationEngine(private val validator: ConversationValidator = ConversationValidator) {
    fun processEvent(
        context: ConversationContext,
        event: ConversationEvent
    ): ConversationResult {
        val errors = validator.validate(context.currentState, event, context)

        if (errors.isNotEmpty()) {
            return ConversationResult(
                nextState = context.currentState,
                updatedContext = context,
                nextAction = ConversationAction.WaitForInput,
                validationErrors = errors
            )
        }

        val nextState = transition(context.currentState, event)
        val updatedContext = context.copy(currentState = nextState)
        val nextAction = determineAction(nextState)

        return ConversationResult(
            nextState = nextState,
            updatedContext = updatedContext,
            nextAction = nextAction
        )
    }

    private fun transition(
        currentState: ConversationState,
        event: ConversationEvent
    ): ConversationState = when (currentState) {
        ConversationState.IDLE -> when (event) {
            is ConversationEvent.MessageReceived -> ConversationState.WAITING_GREETING
            is ConversationEvent.RestartConversation -> ConversationState.IDLE
            else -> currentState
        }
        ConversationState.WAITING_GREETING -> when (event) {
            is ConversationEvent.MessageReceived -> ConversationState.WAITING_ORDER
            else -> currentState
        }
        ConversationState.WAITING_ORDER -> when (event) {
            is ConversationEvent.ProductDetected -> ConversationState.WAITING_MODIFIERS
            else -> currentState
        }
        ConversationState.WAITING_MODIFIERS -> when (event) {
            is ConversationEvent.ModifierDetected -> ConversationState.WAITING_ADDRESS
            else -> currentState
        }
        ConversationState.WAITING_ADDRESS -> when (event) {
            is ConversationEvent.AddressReceived -> ConversationState.WAITING_PAYMENT
            else -> currentState
        }
        ConversationState.WAITING_PAYMENT -> when (event) {
            is ConversationEvent.PaymentSelected -> ConversationState.WAITING_CONFIRMATION
            else -> currentState
        }
        ConversationState.WAITING_CONFIRMATION -> when (event) {
            is ConversationEvent.ConfirmationReceived -> ConversationState.ORDER_CONFIRMED
            is ConversationEvent.CancelOrder -> ConversationState.ORDER_CANCELLED
            else -> currentState
        }
        ConversationState.ORDER_CONFIRMED -> when (event) {
            is ConversationEvent.RestartConversation -> ConversationState.IDLE
            else -> currentState
        }
        ConversationState.ORDER_CANCELLED -> when (event) {
            is ConversationEvent.RestartConversation -> ConversationState.IDLE
            else -> currentState
        }
        ConversationState.SESSION_EXPIRED -> when (event) {
            is ConversationEvent.RestartConversation -> ConversationState.IDLE
            else -> currentState
        }
    }

    private fun determineAction(state: ConversationState): ConversationAction = when (state) {
        ConversationState.WAITING_GREETING,
        ConversationState.WAITING_ORDER,
        ConversationState.WAITING_MODIFIERS,
        ConversationState.WAITING_ADDRESS,
        ConversationState.WAITING_PAYMENT,
        ConversationState.WAITING_CONFIRMATION -> ConversationAction.WaitForInput
        ConversationState.ORDER_CONFIRMED -> ConversationAction.NotifyCashier
        ConversationState.ORDER_CANCELLED -> ConversationAction.CancelSession
        ConversationState.SESSION_EXPIRED -> ConversationAction.CancelSession
        else -> ConversationAction.WaitForInput
    }
}
