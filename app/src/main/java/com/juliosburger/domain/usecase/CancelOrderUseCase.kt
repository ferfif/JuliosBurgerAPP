package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de uso para cancelar un pedido en cualquier fase pre-entrega.
 *
 * Transiciona el [DraftOrder] hacia [DraftOrderStatus.CANCELLED].
 *
 * Regla de negocio: solo una orden en estado DRAFT,
 * PENDING_CASHIER_REVIEW, CONFIRMED, COOKING o READY puede cancelarse.
 * Las órdenes en DELIVERED o CANCELLED no pueden cancelarse.
 */
class CancelOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(draftOrderId: String): Flow<Result<DraftOrder>> = flow {
        val currentOrder = orderRepository.getDraftOrderById(draftOrderId).first()
        if (currentOrder == null) {
            emit(Result.failure(NoSuchElementException("DraftOrder not found: $draftOrderId")))
        } else {
            val cancelableStatuses = setOf(
                DraftOrderStatus.DRAFT,
                DraftOrderStatus.PENDING_CASHIER_REVIEW,
                DraftOrderStatus.CONFIRMED,
                DraftOrderStatus.COOKING,
                DraftOrderStatus.READY
            )
            if (currentOrder.status in cancelableStatuses) {
                orderRepository.updateDraftOrderStatus(draftOrderId, DraftOrderStatus.CANCELLED)
                    .collect { result -> emit(result) }
            } else {
                emit(Result.failure(IllegalStateException("Cannot cancel order in status: ${currentOrder.status}")))
            }
        }
    }
}
