package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class GetDraftOrdersUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(status: DraftOrderStatus = DraftOrderStatus.DRAFT): Flow<List<DraftOrder>> {
        return orderRepository.getDraftOrdersByStatus(status)
    }
}
