package com.juliosburger.domain.core

import java.time.Instant

interface Clock {
    fun now(): Instant
}

class SystemClock : Clock {
    override fun now(): Instant = Instant.now()
}

class FixedClock(private val fixedInstant: Instant) : Clock {
    override fun now(): Instant = fixedInstant
}
