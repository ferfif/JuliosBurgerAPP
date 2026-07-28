package com.juliosburger.data.mapper

import com.juliosburger.data.entity.ModifierGroupEntity
import com.juliosburger.domain.model.ModifierGroup
import java.util.UUID

object ModifierGroupMapper {
    fun mapToDomain(entity: ModifierGroupEntity): ModifierGroup {
        return ModifierGroup(
            id = UUID.fromString(entity.id),
            productId = UUID.fromString(entity.productId),
            name = entity.name,
            minSelection = entity.minSelection,
            maxSelection = entity.maxSelection,
            isRequired = entity.isRequired,
            displayOrder = entity.displayOrder
        )
    }

    fun mapToEntity(domain: ModifierGroup): ModifierGroupEntity {
        return ModifierGroupEntity(
            id = domain.id.toString(),
            productId = domain.productId.toString(),
            name = domain.name,
            minSelection = domain.minSelection,
            maxSelection = domain.maxSelection,
            isRequired = domain.isRequired,
            displayOrder = domain.displayOrder
        )
    }
}
