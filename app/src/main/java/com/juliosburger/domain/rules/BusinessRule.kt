package com.juliosburger.domain.rules

interface BusinessRule {
    val id: String
    val name: String
    val description: String
    val priority: RulePriority
    val category: RuleCategory
    fun validate(context: RuleContext): BusinessRuleResult
}
