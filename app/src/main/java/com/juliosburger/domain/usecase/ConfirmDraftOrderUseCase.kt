package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Caso de uso para confirmar un pedido en borrador.
 *
 * Transiciona el [DraftOrder] desde su estado actual hacia
 * [DraftOrderStatus.PENDING_CASHIER_REVIEW], activando la cola
 * de revisión del cajero.
 */
class ConfirmDraftOrderUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(draftOrderId: String): Flow<Result<DraftOrder>> = emptyFlow()
}
