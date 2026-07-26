package com.juliosburger.domain.session

import com.juliosburger.domain.core.Result
import kotlinx.coroutines.flow.Flow

/**
 * Contrato de persistencia para sesiones efímeras.
 */
interface SessionRepository {
    fun getByPhone(phoneNumber: String): Flow<WhatsAppSession?>
    suspend fun getById(sessionId: String): WhatsAppSession?
    suspend fun save(session: WhatsAppSession): Result<WhatsAppSession>
    suspend fun update(session: WhatsAppSession): Result<WhatsAppSession>
    suspend fun delete(sessionId: String): Result<Unit>
}
