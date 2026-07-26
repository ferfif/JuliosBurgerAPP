package com.juliosburger.domain.rules

interface RuleRegistry {
    suspend fun register(rule: BusinessRule)
    suspend fun getById(ruleId: String): BusinessRule?
    suspend fun getAll(): List<BusinessRule>
    suspend fun getByCategory(category: RuleCategory): List<BusinessRule>
    suspend fun clear()
}
