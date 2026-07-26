package com.juliosburger.domain.complaints

import com.juliosburger.domain.core.Result

class CreateComplaintUseCase(private val repository: ComplaintRepository) {
    suspend operator fun invoke(complaint: Complaint): Result<Complaint> {
        return repository.save(complaint)
    }
}
