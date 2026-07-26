package com.juliosburger.domain.orchestrator

import com.juliosburger.domain.audit.AuditEntry
import com.juliosburger.domain.conversation.ConversationResult
import com.juliosburger.domain.decision.DecisionResult
import com.juliosburger.domain.events.DomainEventResult
import com.juliosburger.domain.recommendation.RecommendationResult
import com.juliosburger.domain.session.WhatsAppSession

data class ConversationOrchestratorResult(
    val updatedSession: WhatsAppSession? = null,
    val conversationResult: ConversationResult? = null,
    val decisionResult: DecisionResult? = null,
    val recommendations: RecommendationResult? = null,
    val events: DomainEventResult? = null,
    val auditEntries: List<AuditEntry> = emptyList(),
    val errors: List<String> = emptyList()
)
