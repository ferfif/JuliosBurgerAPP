package com.juliosburger.domain.audit

import kotlinx.coroutines.flow.Flow

class GetAuditHistoryUseCase(private val repository: AuditRepository) {
    operator fun invoke(query: AuditQuery): Flow<List<AuditEntry>> {
        val filtered = repository.findAll()
        return filtered
    }
}
