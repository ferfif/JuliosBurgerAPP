package com.juliosburger.domain.events

interface DomainEventPublisher {
    suspend fun publish(event: DomainEvent)
    suspend fun publish(events: List<DomainEvent>)
}
