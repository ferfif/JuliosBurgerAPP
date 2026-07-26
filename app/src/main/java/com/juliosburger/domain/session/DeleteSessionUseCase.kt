package com.juliosburger.domain.session

/**
 * Elimina una sesión del repositorio.
 */
class DeleteSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(sessionId: String) {
        repository.delete(sessionId)
    }
}
