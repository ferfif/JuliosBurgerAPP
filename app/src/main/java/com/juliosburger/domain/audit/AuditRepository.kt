package com.juliosburger.domain.audit

import com.juliosburger.domain.core.Identifier
import com.juliosburger.domain.core.Result
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface AuditRepository {
    suspend fun save(entry: AuditEntry): Result<AuditEntry>
    fun findByEntity(entityType: AuditEntityType, entityId: Identifier): Flow<List<AuditEntry>>
    fun findByAction(action: AuditAction): Flow<List<AuditEntry>>
    fun findBetween(start: Instant, end: Instant): Flow<List<AuditEntry>>
    fun findAll(): Flow<List<AuditEntry>>
}
