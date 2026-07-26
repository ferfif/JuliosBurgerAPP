package com.juliosburger.domain.events

interface DomainEventListener<T : DomainEvent> {
    suspend fun handle(event: T)
}
