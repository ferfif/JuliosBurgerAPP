package com.juliosburger.domain.model

/**
 * Estados posibles de un [DraftOrder] según el flujo transaccional y operativo.
 */
enum class DraftOrderStatus {
    DRAFT,
    PENDING_CASHIER_REVIEW,
    CONFIRMED,
    COOKING,
    READY,
    DELIVERED,
    CANCELLED
}
