package com.juliosburger.domain.audit

import com.juliosburger.domain.core.Identifier
import kotlinx.coroutines.flow.Flow

class GetAuditByEntityUseCase(private val repository: AuditRepository) {
    operator fun invoke(entityType: AuditEntityType, entityId: Identifier): Flow<List<AuditEntry>> =
        repository.findByEntity(entityType, entityId)
}
