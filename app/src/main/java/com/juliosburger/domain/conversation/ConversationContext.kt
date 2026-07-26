package com.juliosburger.domain.conversation

import com.juliosburger.domain.model.DraftOrder
import java.time.Instant

/**
 * Contexto efímero de una sesión conversacional.
 *
 * Contiene únicamente información temporal necesaria para el procesamiento
 * del turno actual. No incluye lógica de persistencia ni dependencias externas.
 */
data class ConversationContext(
    val sessionId: String,
    val phoneNumber: String,
    val draftOrder: DraftOrder? = null,
    val currentState: ConversationState,
    val expectedInformation: String? = null,
    val lastInteraction: Instant = Instant.now(),
    val metadata: Map<String, String> = emptyMap()
)
