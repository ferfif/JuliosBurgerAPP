package com.juliosburger.domain.session

/**
 * Actualiza una sesión existente en el repositorio.
 */
class UpdateSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(session: WhatsAppSession) {
        repository.update(session)
    }
}
