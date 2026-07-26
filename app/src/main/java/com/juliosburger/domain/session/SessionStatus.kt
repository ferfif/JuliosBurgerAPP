package com.juliosburger.domain.session

/**
 * Ciclo de vida operativo de una sesión de WhatsApp.
 */
enum class SessionStatus {
    ACTIVE,
    EXPIRED,
    CLOSED
}
