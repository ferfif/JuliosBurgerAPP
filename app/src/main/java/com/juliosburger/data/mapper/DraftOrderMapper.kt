package com.juliosburger.data.mapper

import com.juliosburger.data.entity.DraftOrderEntity
import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import java.time.Instant
import java.util.UUID

object DraftOrderMapper {
    fun mapToDomain(entity: DraftOrderEntity): DraftOrder {
        return DraftOrder(
            id = UUID.fromString(entity.id),
            customerPhone = entity.customerPhone,
            customerName = entity.customerName,
            deliveryAddress = entity.deliveryAddress,
            paymentMethod = entity.paymentMethod,
            status = DraftOrderStatus.valueOf(entity.status),
            items = emptyList(),
            cashierNotes = entity.cashierNotes,
            createdAt = Instant.ofEpochMilli(entity.createdAt),
            updatedAt = Instant.ofEpochMilli(entity.updatedAt)
        )
    }

    fun mapToEntity(domain: DraftOrder): DraftOrderEntity {
        return DraftOrderEntity(
            id = domain.id.toString(),
            customerPhone = domain.customerPhone,
            customerName = domain.customerName,
            deliveryAddress = domain.deliveryAddress,
            paymentMethod = domain.paymentMethod,
            status = domain.status.name,
            cashierNotes = domain.cashierNotes,
            createdAt = domain.createdAt.toEpochMilli(),
            updatedAt = domain.updatedAt.toEpochMilli()
        )
    }
}
