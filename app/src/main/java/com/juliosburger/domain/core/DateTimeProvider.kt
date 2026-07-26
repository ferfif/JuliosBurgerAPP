package com.juliosburger.domain.core

import java.time.Instant

class DateTimeProvider(private val clock: Clock = SystemClock()) {
    fun now(): Instant = clock.now()
}
