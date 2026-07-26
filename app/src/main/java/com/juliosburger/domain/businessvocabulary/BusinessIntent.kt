package com.juliosburger.domain.businessvocabulary

/**
 * Intenciones de negocio reconocidas por el sistema.
 *
 * Representan las acciones que el cliente puede expresar
 * dentro del flujo conversacional del restaurante.
 */
enum class BusinessIntent {
    ADD_MODIFIERS,
    ADD_PRODUCT,
    CHANGE_ADDRESS,
    CHANGE_PAYMENT,
    CANCEL_ORDER,
    GREETING,
    UNKNOWN
}
