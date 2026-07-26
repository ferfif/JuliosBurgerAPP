package com.juliosburger.domain.rules

interface BusinessRuleEngine {
    suspend fun validate(context: RuleContext): RuleExecutionResult
}
