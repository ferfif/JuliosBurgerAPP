package com.juliosburger.domain.core

data class DomainError(
    val code: ErrorCode,
    val message: String,
    val cause: Throwable? = null
)
