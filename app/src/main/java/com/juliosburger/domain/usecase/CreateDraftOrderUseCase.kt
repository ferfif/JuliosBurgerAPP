package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderItem
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Caso de uso para crear un nuevo pedido en borrador.
 *
 * Transforma la entrada del cliente (productos, modificadores, datos de contacto)
 * en una entidad [DraftOrder] inicial en estado [DraftOrderStatus.DRAFT].
 */
class CreateDraftOrderUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(params: Params): Flow<DraftOrder> {
        val order = DraftOrder(
            customerPhone = params.customerPhone,
            customerName = params.customerName,
            deliveryAddress = params.deliveryAddress,
            paymentMethod = params.paymentMethod,
            status = DraftOrderStatus.DRAFT,
            items = params.items
        )
        return orderRepository.saveDraftOrder(order).map { result ->
            result.getOrThrow()
        }
    }

    data class Params(
        val customerPhone: String,
        val customerName: String? = null,
        val items: List<DraftOrderItem>,
        val deliveryAddress: String? = null,
        val paymentMethod: String? = null
    )
}
