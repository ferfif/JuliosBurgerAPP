package com.juliosburger.domain.decision

/**
 * Pasos oficiales de resolución de decisiones en el flujo conversacional.
 *
 * El orden determina la prioridad de interpretación de mensajes del cliente.
 */
enum class DecisionResolutionStep {
    BUSINESS_VOCABULARY,
    DOMAIN_RULES,
    CUSTOMER_MEMORY,
    LLM
}
