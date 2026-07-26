package com.juliosburger.domain.core

data class PhoneNumber(val value: String) {
    init {
        require(value.matches(Regex("^\\+?[1-9]\\d{6,14}$"))) { "Invalid phone number format" }
    }
}
