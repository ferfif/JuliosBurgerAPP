package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de uso para marcar como entregado un pedido listo.
 *
 * Transiciona el [DraftOrder] desde [DraftOrderStatus.READY]
 * hacia [DraftOrderStatus.DELIVERED], activando la cola de entregados.
 *
 * Regla de negocio: solo una orden en estado READY puede marcarse como entregada.
 */
class DeliverOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(draftOrderId: String): Flow<Result<DraftOrder>> = flow {
        val currentOrder = orderRepository.getDraftOrderById(draftOrderId).first()
        if (currentOrder == null) {
            emit(Result.failure(NoSuchElementException("DraftOrder not found: $draftOrderId")))
        } else if (currentOrder.status != DraftOrderStatus.READY) {
            emit(Result.failure(IllegalStateException("Cannot deliver order in status: ${currentOrder.status}")))
        } else {
            orderRepository.updateDraftOrderStatus(draftOrderId, DraftOrderStatus.DELIVERED)
                .collect { result -> emit(result) }
        }
    }
}
