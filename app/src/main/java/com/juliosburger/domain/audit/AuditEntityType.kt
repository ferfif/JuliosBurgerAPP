package com.juliosburger.domain.audit

/**
 * Tipos de entidad que pueden ser auditadas.
 */
enum class AuditEntityType {
    ORDER,
    PRODUCT,
    CATEGORY,
    PROMOTION,
    BUSINESS_VOCABULARY,
    CUSTOMER,
    SESSION,
    CONFIGURATION,
    USER,
    SYSTEM
}
