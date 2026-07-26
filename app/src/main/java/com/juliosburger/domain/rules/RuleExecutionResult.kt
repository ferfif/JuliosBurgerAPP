package com.juliosburger.domain.rules

data class RuleExecutionResult(
    val rulesExecuted: List<BusinessRuleResult>,
    val approved: List<BusinessRuleResult>,
    val failed: List<BusinessRuleResult>,
    val violations: List<RuleViolation>,
    val isSuccessful: Boolean
)
