package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.DraftOrderItem
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class BuildDraftOrderItemUseCase @Inject constructor() {
    suspend operator fun invoke(params: Params): Flow<DraftOrderItem> = flow {
        validate(params)
        emit(
            DraftOrderItem(
                productSnapshot = params.product,
                quantity = params.quantity,
                modifierSnapshot = params.modifierOptions
            )
        )
    }

    private fun validate(params: Params) {
        val groupModifiers = params.modifierOptions.filter {
            it.modifierGroupId == params.currentGroupId
        }
        val count = groupModifiers.size
        if (count < params.minSelection) {
            throw IllegalArgumentException(
                "Debe seleccionar al menos ${params.minSelection} opción(es)"
            )
        }
        if (count > params.maxSelection) {
            throw IllegalArgumentException(
                "No puede seleccionar más de ${params.maxSelection} opción(es)"
            )
        }
    }

    data class Params(
        val product: Product,
        val modifierOptions: List<ModifierOption>,
        val quantity: Int = 1,
        val currentGroupId: UUID? = null,
        val minSelection: Int = 0,
        val maxSelection: Int = Int.MAX_VALUE
    )
}
