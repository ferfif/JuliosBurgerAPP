package com.juliosburger.domain.core

open class DomainException(
    val error: DomainError,
    cause: Throwable? = null
) : RuntimeException(error.message, cause)

class ValidationException(
    message: String,
    cause: Throwable? = null
) : DomainException(DomainError(ErrorCode.VALIDATION_ERROR, message), cause)

class NotFoundException(
    message: String,
    cause: Throwable? = null
) : DomainException(DomainError(ErrorCode.NOT_FOUND, message), cause)

class BusinessRuleViolationException(
    message: String,
    cause: Throwable? = null
) : DomainException(DomainError(ErrorCode.BUSINESS_RULE_VIOLATION, message), cause)
