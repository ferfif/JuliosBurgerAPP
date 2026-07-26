package com.juliosburger.domain.decision

import com.juliosburger.domain.conversation.ConversationContext
import com.juliosburger.domain.model.DraftOrder

/**
 * Representa toda la información necesaria para tomar una decisión conversacional.
 */
data class DecisionRequest(
    val message: String,
    val context: ConversationContext,
    val draftOrder: DraftOrder? = null,
    val customerMemory: com.juliosburger.domain.customermemory.CustomerMemory? = null,
    val session: com.juliosburger.domain.session.WhatsAppSession? = null
)
