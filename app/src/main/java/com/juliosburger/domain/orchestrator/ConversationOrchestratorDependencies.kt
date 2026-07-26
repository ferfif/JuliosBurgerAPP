package com.juliosburger.domain.orchestrator

import com.juliosburger.domain.audit.AuditRepository
import com.juliosburger.domain.businessvocabulary.BusinessVocabularyMatcher
import com.juliosburger.domain.conversation.ConversationEngine
import com.juliosburger.domain.customermemory.CustomerMemoryRepository
import com.juliosburger.domain.decision.DecisionResolver
import com.juliosburger.domain.events.DomainEventPublisher
import com.juliosburger.domain.recommendation.RecommendationEngine
import com.juliosburger.domain.rules.BusinessRuleEngine
import com.juliosburger.domain.session.SessionRepository

data class ConversationOrchestratorDependencies(
    val conversationEngine: ConversationEngine,
    val businessRuleEngine: BusinessRuleEngine,
    val decisionResolver: DecisionResolver,
    val recommendationEngine: RecommendationEngine,
    val businessVocabularyMatcher: BusinessVocabularyMatcher,
    val customerMemoryRepository: CustomerMemoryRepository,
    val auditRepository: AuditRepository,
    val domainEventPublisher: DomainEventPublisher,
    val sessionRepository: SessionRepository
)
