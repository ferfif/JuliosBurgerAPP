package com.juliosburger.domain.core

import java.math.BigDecimal
import java.util.Currency

data class Money(val amount: BigDecimal, val currency: Currency = Currency.getInstance("USD")) {
    init {
        require(amount >= BigDecimal.ZERO) { "Amount cannot be negative" }
    }

    operator fun plus(other: Money): Money {
        require(currency == other.currency) { "Currency mismatch" }
        return Money(amount + other.amount, currency)
    }

    operator fun minus(other: Money): Money {
        require(currency == other.currency) { "Currency mismatch" }
        return Money(amount - other.amount, currency)
    }
}
