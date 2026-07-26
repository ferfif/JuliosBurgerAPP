package com.juliosburger.domain.session

/**
 * Recupera una sesión existente desde el repositorio.
 */
class GetSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(sessionId: String): WhatsAppSession? =
        repository.getById(sessionId)
}
