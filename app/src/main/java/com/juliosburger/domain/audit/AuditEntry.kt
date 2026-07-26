package com.juliosburger.domain.audit

import com.juliosburger.domain.core.Identifier
import java.time.Instant

data class AuditEntry(
    val id: Identifier,
    val action: AuditAction,
    val entityType: AuditEntityType,
    val entityId: Identifier? = null,
    val performedBy: String,
    val performedAt: Instant,
    val previousValue: String? = null,
    val newValue: String? = null,
    val reason: String? = null,
    val metadata: AuditMetadata
)
