package com.juliosburger.data.mapper

import com.juliosburger.data.entity.CategoryEntity
import com.juliosburger.domain.model.Category
import java.util.UUID

object CategoryMapper {
    fun mapToDomain(entity: CategoryEntity): Category {
        return Category(
            id = UUID.fromString(entity.id),
            name = entity.name,
            description = entity.description,
            displayOrder = entity.displayOrder,
            isActive = entity.isActive
        )
    }

    fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(
            id = domain.id.toString(),
            name = domain.name,
            description = domain.description,
            displayOrder = domain.displayOrder,
            isActive = domain.isActive
        )
    }
}
