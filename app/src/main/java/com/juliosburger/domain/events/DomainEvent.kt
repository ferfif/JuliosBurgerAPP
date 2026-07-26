package com.juliosburger.domain.events

import java.time.Instant
import java.util.UUID

interface DomainEvent {
    val eventId: UUID
    val occurredAt: Instant
    val eventType: DomainEventType
}
