package com.juliosburger.domain.decision

/**
 * Secuencia oficial de pasos de resolución.
 */
object DecisionPipeline {
    val steps: List<DecisionResolutionStep> = listOf(
        DecisionResolutionStep.BUSINESS_VOCABULARY,
        DecisionResolutionStep.DOMAIN_RULES,
        DecisionResolutionStep.CUSTOMER_MEMORY,
        DecisionResolutionStep.LLM
    )
}
