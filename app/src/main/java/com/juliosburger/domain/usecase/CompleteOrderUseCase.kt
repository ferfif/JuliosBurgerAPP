package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de uso para marcar como listo un pedido en cocina.
 *
 * Transiciona el [DraftOrder] desde [DraftOrderStatus.COOKING]
 * hacia [DraftOrderStatus.READY], activando la cola de listos.
 *
 * Regla de negocio: solo una orden en estado COOKING puede marcarse como lista.
 */
class CompleteOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(draftOrderId: String): Flow<Result<DraftOrder>> = flow {
        val currentOrder = orderRepository.getDraftOrderById(draftOrderId).first()
        if (currentOrder == null) {
            emit(Result.failure(NoSuchElementException("DraftOrder not found: $draftOrderId")))
        } else if (currentOrder.status != DraftOrderStatus.COOKING) {
            emit(Result.failure(IllegalStateException("Cannot complete order in status: ${currentOrder.status}")))
        } else {
            orderRepository.updateDraftOrderStatus(draftOrderId, DraftOrderStatus.READY)
                .collect { result -> emit(result) }
        }
    }
}
