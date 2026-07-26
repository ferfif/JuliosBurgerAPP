package com.juliosburger.domain.rules

data class RuleSet(
    val category: RuleCategory,
    val rules: List<BusinessRule>
)
