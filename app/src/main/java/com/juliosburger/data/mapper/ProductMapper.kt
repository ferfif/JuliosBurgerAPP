package com.juliosburger.data.mapper

import com.juliosburger.data.entity.ProductEntity
import com.juliosburger.domain.model.Product
import java.util.UUID

object ProductMapper {
    fun mapToDomain(entity: ProductEntity): Product {
        return Product(
            id = UUID.fromString(entity.id),
            categoryId = UUID.fromString(entity.categoryId),
            name = entity.name,
            description = entity.description,
            basePrice = entity.basePrice,
            imageUrl = entity.imageUrl,
            isAvailable = entity.isAvailable,
            displayOrder = entity.displayOrder
        )
    }

    fun mapToEntity(domain: Product): ProductEntity {
        return ProductEntity(
            id = domain.id.toString(),
            categoryId = domain.categoryId.toString(),
            name = domain.name,
            description = domain.description,
            basePrice = domain.basePrice,
            imageUrl = domain.imageUrl,
            isAvailable = domain.isAvailable,
            displayOrder = domain.displayOrder
        )
    }
}
