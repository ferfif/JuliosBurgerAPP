package com.juliosburger.domain.orchestrator

data class ConversationOrchestratorRequest(
    val sessionId: String,
    val phoneNumber: String,
    val message: String,
    val timestamp: java.time.Instant = java.time.Instant.now(),
    val metadata: Map<String, String> = emptyMap()
)
