package com.juliosburger.data.mapper

import com.juliosburger.data.entity.CustomerEntity
import com.juliosburger.domain.model.Customer
import java.time.Instant
import java.util.UUID

object CustomerMapper {
    fun mapToDomain(entity: CustomerEntity): Customer {
        return Customer(
            id = UUID.fromString(entity.id),
            phone = entity.phone,
            name = entity.name,
            favoriteAddress = entity.favoriteAddress,
            favoritePaymentMethod = entity.favoritePaymentMethod,
            lastPurchaseAt = entity.lastPurchaseAt?.let { Instant.ofEpochMilli(it) },
            purchaseFrequency = entity.purchaseFrequency,
            confidenceScore = entity.confidenceScore
        )
    }

    fun mapToEntity(domain: Customer): CustomerEntity {
        return CustomerEntity(
            id = domain.id.toString(),
            phone = domain.phone,
            name = domain.name,
            favoriteAddress = domain.favoriteAddress,
            favoritePaymentMethod = domain.favoritePaymentMethod,
            lastPurchaseAt = domain.lastPurchaseAt?.toEpochMilli(),
            purchaseFrequency = domain.purchaseFrequency,
            confidenceScore = domain.confidenceScore
        )
    }
}
