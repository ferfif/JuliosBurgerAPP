package com.juliosburger.domain.repository

import com.juliosburger.domain.model.RestaurantStatus
import kotlinx.coroutines.flow.Flow

/**
 * Puerto de salida para la configuración dinámica del restaurante.
 *
 * Gestiona parámetros operativos como el estado del local,
 * horarios, radios de entrega y reglas de negocio configurables.
 */
interface ConfigurationRepository {
    suspend fun getRestaurantStatus(): Flow<RestaurantStatus>
    suspend fun updateRestaurantStatus(status: RestaurantStatus): Flow<Result<RestaurantStatus>>
}
