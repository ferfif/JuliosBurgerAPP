package com.juliosburger.domain.model

/**
 * Estado operativo del restaurante que afecta la disponibilidad del asistente.
 */
enum class RestaurantStatus {
    OPEN,
    CLOSED,
    STOP_ACCEPTING_ORDERS
}
