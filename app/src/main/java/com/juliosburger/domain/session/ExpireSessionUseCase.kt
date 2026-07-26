package com.juliosburger.domain.session

import com.juliosburger.domain.core.DomainError
import com.juliosburger.domain.core.ErrorCode
import com.juliosburger.domain.core.Result

/**
 * Marca una sesión como expirada sin eliminar su información.
 */
class ExpireSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(sessionId: String): Result<WhatsAppSession> {
        val session = repository.getById(sessionId)
            ?: return Result.Failure(
                DomainError(
                    code = ErrorCode.NOT_FOUND,
                    message = "Session not found"
                )
            )

        val expired = session.copy(
            metadata = session.metadata + ("status" to SessionStatus.EXPIRED.name)
        )

        return repository.update(expired)
    }
}
