package com.juliosburger.domain.complaints

import com.juliosburger.domain.core.Identifier
import kotlinx.coroutines.flow.Flow

class UpdateComplaintStatusUseCase(private val repository: ComplaintRepository) {
    suspend operator fun invoke(id: Identifier, status: ComplaintStatus): Complaint? {
        val current = repository.getById(id) ?: return null
        val updated = current.copy(status = status)
        val result = repository.save(updated)
        return when (result) {
            is com.juliosburger.domain.core.Result.Success -> result.value
            is com.juliosburger.domain.core.Result.Failure -> null
        }
    }
}
