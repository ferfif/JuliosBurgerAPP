package com.juliosburger.domain.conversation

/**
 * Eventos que ingresan al Conversation Engine.
 *
 * Representan señales externas que pueden modificar el estado conversacional.
 */
sealed class ConversationEvent {
    data class MessageReceived(val message: String) : ConversationEvent()
    data class ProductDetected(val productId: String) : ConversationEvent()
    data class ModifierDetected(val modifierIds: List<String>) : ConversationEvent()
    data class AddressReceived(val address: String) : ConversationEvent()
    data class PaymentSelected(val paymentMethod: String) : ConversationEvent()
    object ConfirmationReceived : ConversationEvent()
    object CancelOrder : ConversationEvent()
    object Timeout : ConversationEvent()
    object RestartConversation : ConversationEvent()
}
