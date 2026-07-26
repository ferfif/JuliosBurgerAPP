package com.juliosburger.domain.repository

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import kotlinx.coroutines.flow.Flow

/**
 * Puerto de salida para persistir y recuperar pedidos en borrador.
 *
 * Gestiona el ciclo de vida completo del [DraftOrder] desde su creación
 * hasta su entrega o cancelación.
 */
interface OrderRepository {
    suspend fun saveDraftOrder(order: DraftOrder): Flow<Result<DraftOrder>>
    suspend fun getDraftOrderById(id: String): Flow<DraftOrder?>
    suspend fun getDraftOrdersByStatus(status: DraftOrderStatus): Flow<List<DraftOrder>>
    suspend fun updateDraftOrderStatus(id: String, status: DraftOrderStatus): Flow<Result<DraftOrder>>
}
