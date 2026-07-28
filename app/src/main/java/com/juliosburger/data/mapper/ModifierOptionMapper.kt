package com.juliosburger.data.mapper

import com.juliosburger.data.entity.ModifierOptionEntity
import com.juliosburger.domain.model.ModifierOption
import java.util.UUID

object ModifierOptionMapper {
    fun mapToDomain(entity: ModifierOptionEntity): ModifierOption {
        return ModifierOption(
            id = UUID.fromString(entity.id),
            modifierGroupId = UUID.fromString(entity.modifierGroupId),
            name = entity.name,
            priceAdjustment = entity.priceAdjustment,
            isDefault = entity.isDefault,
            isActive = entity.isActive
        )
    }

    fun mapToEntity(domain: ModifierOption): ModifierOptionEntity {
        return ModifierOptionEntity(
            id = domain.id.toString(),
            modifierGroupId = domain.modifierGroupId.toString(),
            name = domain.name,
            priceAdjustment = domain.priceAdjustment,
            isDefault = domain.isDefault,
            isActive = domain.isActive
        )
    }
}
