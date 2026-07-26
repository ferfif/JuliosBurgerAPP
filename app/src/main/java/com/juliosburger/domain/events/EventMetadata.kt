package com.juliosburger.domain.events

import java.time.Instant
import java.util.UUID

data class EventMetadata(
    val timestamp: Instant,
    val correlationId: UUID,
    val causationId: UUID? = null,
    val source: String,
    val version: String
)
