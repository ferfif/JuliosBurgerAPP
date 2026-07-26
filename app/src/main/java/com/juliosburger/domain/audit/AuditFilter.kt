package com.juliosburger.domain.audit

import com.juliosburger.domain.core.Identifier
import java.time.Instant

data class AuditFilter(
    val startDate: Instant? = null,
    val endDate: Instant? = null,
    val performedBy: String? = null,
    val action: AuditAction? = null,
    val entityType: AuditEntityType? = null,
    val entityId: Identifier? = null
)
