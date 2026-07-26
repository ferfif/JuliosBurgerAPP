package com.juliosburger.domain.decision

/**
 * Políticas generales del proceso de resolución de decisiones.
 */
data class DecisionPolicy(
    val minConfidence: Double = 0.8,
    val allowFallback: Boolean = true,
    val useMemory: Boolean = true,
    val useLLM: Boolean = true
)
