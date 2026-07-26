package com.juliosburger.domain.rules

data class BusinessRuleResult(
    val ruleId: String,
    val passed: Boolean,
    val message: String,
    val errorCode: String? = null,
    val severity: RulePriority? = null,
    val violations: List<RuleViolation> = emptyList()
)
