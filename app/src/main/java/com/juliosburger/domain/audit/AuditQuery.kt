package com.juliosburger.domain.audit

data class AuditQuery(
    val filter: AuditFilter,
    val pageSize: Int = 50,
    val offset: Int = 0
)
