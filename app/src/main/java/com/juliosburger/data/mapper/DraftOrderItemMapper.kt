package com.juliosburger.data.mapper

import com.juliosburger.data.entity.DraftOrderItemEntity
import com.juliosburger.data.serialization.KotlinxSnapshotSerializer
import com.juliosburger.data.serialization.SnapshotSerializer
import com.juliosburger.data.snapshot.ModifierOptionSnapshotDto
import com.juliosburger.data.snapshot.ProductSnapshotDto
import com.juliosburger.domain.model.DraftOrderItem
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.model.Product
import java.util.UUID
import kotlinx.serialization.builtins.ListSerializer

class DraftOrderItemMapper(
    private val serializer: SnapshotSerializer = KotlinxSnapshotSerializer()
) {
    fun mapToDomain(entity: DraftOrderItemEntity): DraftOrderItem {
        val productDto = serializer.deserialize(
            entity.productSnapshot,
            ProductSnapshotDto.serializer()
        )

        val product = Product(
            id = UUID.fromString(productDto.id),
            categoryId = UUID.fromString(productDto.categoryId),
            name = productDto.name,
            description = productDto.description,
            basePrice = productDto.basePrice,
            imageUrl = productDto.imageUrl,
            isAvailable = productDto.isAvailable,
            displayOrder = productDto.displayOrder
        )

        val modifierDtos = serializer.deserialize(
            entity.modifierSnapshot,
            ListSerializer(ModifierOptionSnapshotDto.serializer())
        )

        val modifiers = modifierDtos.map { dto ->
            ModifierOption(
                id = UUID.fromString(dto.id),
                modifierGroupId = UUID.fromString(dto.modifierGroupId),
                name = dto.name,
                priceAdjustment = dto.priceAdjustment,
                isDefault = dto.isDefault,
                isActive = dto.isActive
            )
        }

        return DraftOrderItem(
            id = UUID.fromString(entity.id),
            productSnapshot = product,
            quantity = entity.quantity,
            modifierSnapshot = modifiers,
            notes = entity.notes
        )
    }

    fun mapToEntity(domain: DraftOrderItem): DraftOrderItemEntity {
        val productDto = ProductSnapshotDto(
            id = domain.productSnapshot.id.toString(),
            categoryId = domain.productSnapshot.categoryId.toString(),
            name = domain.productSnapshot.name,
            description = domain.productSnapshot.description,
            basePrice = domain.productSnapshot.basePrice,
            imageUrl = domain.productSnapshot.imageUrl,
            isAvailable = domain.productSnapshot.isAvailable,
            displayOrder = domain.productSnapshot.displayOrder
        )

        val modifierDtos = domain.modifierSnapshot.map { modifier ->
            ModifierOptionSnapshotDto(
                id = modifier.id.toString(),
                modifierGroupId = modifier.modifierGroupId.toString(),
                name = modifier.name,
                priceAdjustment = modifier.priceAdjustment,
                isDefault = modifier.isDefault,
                isActive = modifier.isActive
            )
        }

        return DraftOrderItemEntity(
            id = domain.id.toString(),
            draftOrderId = "",
            productSnapshot = serializer.serialize(productDto, ProductSnapshotDto.serializer()),
            quantity = domain.quantity,
            modifierSnapshot = serializer.serialize(modifierDtos, ListSerializer(ModifierOptionSnapshotDto.serializer())),
            notes = domain.notes
        )
    }
}
