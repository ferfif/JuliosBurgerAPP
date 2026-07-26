package com.juliosburger.domain.orchestrator

import com.juliosburger.domain.audit.AuditEntry
import com.juliosburger.domain.conversation.ConversationResult
import com.juliosburger.domain.decision.DecisionResult
import com.juliosburger.domain.events.DomainEventResult
import com.juliosburger.domain.recommendation.RecommendationResult

/**
 * Conversation Orchestrator.
 *
 * Componente central que coordina el flujo conversacional según la prioridad oficial del documento.
 * No contiene lógica de negocio; únicamente define el pipeline de ejecución.
 */
class ConversationOrchestrator(
    private val dependencies: ConversationOrchestratorDependencies
) {
    suspend fun process(
        request: ConversationOrchestratorRequest
    ): ConversationOrchestratorResult {
        return ConversationOrchestratorResult(
            updatedSession = null,
            conversationResult = null,
            decisionResult = null,
            recommendations = null,
            events = null,
            auditEntries = emptyList(),
            errors = emptyList()
        )
    }
}
