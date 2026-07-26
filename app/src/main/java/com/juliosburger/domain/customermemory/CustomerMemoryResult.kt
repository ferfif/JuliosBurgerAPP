package com.juliosburger.domain.customermemory

/**
 * Resultado de consultar o actualizar la memoria de un cliente.
 */
data class CustomerMemoryResult(
    val memory: CustomerMemory?,
    val wasFound: Boolean,
    val applicablePreferences: List<CustomerPreference> = emptyList(),
    val canSuggest: Boolean = false
)
