package com.juliosburger.domain.orchestrator

/**
 * Pasos oficiales del Conversation Orchestrator.
 *
 * El orden define la secuencia inalterable del flujo conversacional.
 */
enum class ConversationOrchestratorStep {
    START,
    LOAD_SESSION,
    MATCH_BUSINESS_VOCABULARY,
    VALIDATE_RULES,
    LOAD_CUSTOMER_MEMORY,
    GENERATE_RECOMMENDATIONS,
    RESOLVE_DECISION,
    PROCESS_CONVERSATION,
    PUBLISH_EVENTS,
    WRITE_AUDIT,
    FINISH
}
