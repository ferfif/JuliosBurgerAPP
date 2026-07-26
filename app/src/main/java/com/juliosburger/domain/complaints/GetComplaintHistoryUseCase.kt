package com.juliosburger.domain.complaints

import kotlinx.coroutines.flow.Flow

class GetComplaintHistoryUseCase(private val repository: ComplaintRepository) {
    operator fun invoke(filter: ComplaintFilter): Flow<List<Complaint>> {
        return repository.filter(filter)
    }
}
