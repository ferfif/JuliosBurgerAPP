package com.juliosburger.domain.audit

import java.util.UUID

data class AuditMetadata(
    val correlationId: UUID,
    val causationId: UUID? = null,
    val source: String,
    val ipAddress: String? = null,
    val device: String? = null,
    val additionalData: Map<String, String> = emptyMap()
)
