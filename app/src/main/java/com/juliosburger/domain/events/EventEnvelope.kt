package com.juliosburger.domain.events

data class EventEnvelope<T : DomainEvent>(
    val metadata: EventMetadata,
    val event: T
)
