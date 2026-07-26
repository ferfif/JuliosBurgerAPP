package com.juliosburger.domain.session

import com.juliosburger.domain.conversation.ConversationState
import com.juliosburger.domain.model.DraftOrder
import java.time.Instant

/**
 * Representa el estado efímero de una conversación de WhatsApp.
 *
 * No almacena información permanente ni está vinculada a historiales
 * de pedidos o memoria del cliente. Expira según las políticas del sistema.
 */
data class WhatsAppSession(
    val sessionId: String,
    val phoneNumber: String,
    val currentState: ConversationState,
    val draftOrder: DraftOrder? = null,
    val createdAt: Instant,
    val lastInteraction: Instant,
    val expiresAt: Instant,
    val expectedInformation: String? = null,
    val metadata: Map<String, String> = emptyMap()
)
