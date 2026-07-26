package com.juliosburger.domain.core

import java.util.UUID

data class Identifier(val value: UUID) {
    companion object {
        fun generate(): Identifier = Identifier(UUID.randomUUID())
        fun fromString(value: String): Identifier = Identifier(UUID.fromString(value))
    }
}
