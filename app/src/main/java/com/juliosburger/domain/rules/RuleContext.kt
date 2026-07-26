package com.juliosburger.domain.rules

/**
 * Contexto de evaluación para las reglas del negocio.
 *
 * Agrupa toda la información necesaria para evaluar una regla usando únicamente objetos del dominio.
 */
data class RuleContext(
    val conversationContext: com.juliosburger.domain.conversation.ConversationContext? = null,
    val session: com.juliosburger.domain.session.WhatsAppSession? = null,
    val draftOrder: com.juliosburger.domain.model.DraftOrder? = null,
    val customerMemory: com.juliosburger.domain.customermemory.CustomerMemory? = null,
    val restaurantStatus: com.juliosburger.domain.model.RestaurantStatus? = null
)
