package com.juliosburger.domain.audit

class RecordAuditEntryUseCase(private val repository: AuditRepository) {
    suspend operator fun invoke(entry: AuditEntry) = repository.save(entry)
}
