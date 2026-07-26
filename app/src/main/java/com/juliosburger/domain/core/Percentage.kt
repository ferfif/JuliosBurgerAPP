package com.juliosburger.domain.core

data class Percentage(val value: Double) {
    init {
        require(value in 0.0..100.0) { "Percentage must be between 0 and 100" }
    }
}
