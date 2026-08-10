package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de uso para aceptar un pedido pendiente de revisión.
 *
 * Transiciona el [DraftOrder] desde [DraftOrderStatus.PENDING_CASHIER_REVIEW]
 * hacia [DraftOrderStatus.CONFIRMED], indicando que el cajero ha revisado
 * y aceptado el pedido.
 *
 * Regla de negocio: solo una orden en estado PENDING_CASHIER_REVIEW puede ser aceptada.
 */
class AcceptDraftOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(draftOrderId: String): Flow<Result<DraftOrder>> = flow {
        val currentOrder = orderRepository.getDraftOrderById(draftOrderId).first()
        if (currentOrder == null) {
            emit(Result.failure(NoSuchElementException("DraftOrder not found: $draftOrderId")))
        } else if (currentOrder.status != DraftOrderStatus.PENDING_CASHIER_REVIEW) {
            emit(Result.failure(IllegalStateException("Cannot accept order in status: ${currentOrder.status}")))
        } else {
            orderRepository.updateDraftOrderStatus(draftOrderId, DraftOrderStatus.CONFIRMED)
                .collect { result -> emit(result) }
        }
    }
}
