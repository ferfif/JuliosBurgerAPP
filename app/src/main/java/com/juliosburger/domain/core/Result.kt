package com.juliosburger.domain.core

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val error: DomainError) : Result<Nothing>
}
