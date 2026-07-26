package com.juliosburger.domain.session

import com.juliosburger.domain.core.DateTimeProvider
import java.time.Instant

/**
 * Política de expiración de sesiones de mensajería.
 *
 * Centraliza las reglas de vigencia sin acceder directamente al reloj del sistema.
 */
class SessionExpirationPolicy(
    private val ttlMillis: Long = 12 * 60 * 60 * 1000,
    private val dateTimeProvider: DateTimeProvider = DateTimeProvider()
) {
    fun isExpired(session: WhatsAppSession): Boolean {
        val now = dateTimeProvider.now()
        return now.isAfter(session.expiresAt) || now.isAfter(session.lastInteraction.plusMillis(ttlMillis))
    }

    fun computeExpiration(from: Instant = dateTimeProvider.now()): Instant {
        return from.plusMillis(ttlMillis)
    }
}
