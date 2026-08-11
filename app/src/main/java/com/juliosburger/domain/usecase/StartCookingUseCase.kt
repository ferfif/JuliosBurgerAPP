package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de uso para iniciar la cocina de un pedido confirmado.
 *
 * Transiciona el [DraftOrder] desde [DraftOrderStatus.CONFIRMED]
 * hacia [DraftOrderStatus.COOKING], activando la cola de cocina.
 *
 * Regla de negocio: solo una orden en estado CONFIRMED puede iniciar cocina.
 */
class StartCookingUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(draftOrderId: String): Flow<Result<DraftOrder>> = flow {
        val currentOrder = orderRepository.getDraftOrderById(draftOrderId).first()
        if (currentOrder == null) {
            emit(Result.failure(NoSuchElementException("DraftOrder not found: $draftOrderId")))
        } else if (currentOrder.status != DraftOrderStatus.CONFIRMED) {
            emit(Result.failure(IllegalStateException("Cannot start cooking order in status: ${currentOrder.status}")))
        } else {
            orderRepository.updateDraftOrderStatus(draftOrderId, DraftOrderStatus.COOKING)
                .collect { result -> emit(result) }
        }
    }
}
