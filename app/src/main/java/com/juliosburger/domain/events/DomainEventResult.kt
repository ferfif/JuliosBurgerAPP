package com.juliosburger.domain.events

data class DomainEventResult(
    val success: Boolean,
    val publishedEvents: List<DomainEvent>,
    val errors: List<String> = emptyList()
)
