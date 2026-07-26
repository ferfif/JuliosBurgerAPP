package com.juliosburger.domain.events

interface EventDispatcher {
    suspend fun dispatch(event: DomainEvent)
    suspend fun dispatch(events: List<DomainEvent>)
}
