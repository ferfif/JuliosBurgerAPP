package com.juliosburger.domain.rules

data class RuleViolation(
    val ruleId: String,
    val message: String,
    val severity: RulePriority,
    val contextSnapshot: Map<String, Any> = emptyMap()
)
