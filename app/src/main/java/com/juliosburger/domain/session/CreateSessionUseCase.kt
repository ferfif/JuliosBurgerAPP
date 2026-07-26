package com.juliosburger.domain.session

import com.juliosburger.domain.core.DateTimeProvider
import com.juliosburger.domain.conversation.ConversationState

/**
 * Crea una nueva sesión efímera sin persistirla.
 */
class CreateSessionUseCase(private val dateTimeProvider: DateTimeProvider = DateTimeProvider()) {
    operator fun invoke(
        sessionId: String,
        phoneNumber: String,
        initialMetadata: Map<String, String> = emptyMap()
    ): WhatsAppSession {
        val now = dateTimeProvider.now()
        return WhatsAppSession(
            sessionId = sessionId,
            phoneNumber = phoneNumber,
            currentState = ConversationState.IDLE,
            createdAt = now,
            lastInteraction = now,
            expiresAt = now.plusMillis(12 * 60 * 60 * 1000),
            metadata = initialMetadata
        )
    }
}
