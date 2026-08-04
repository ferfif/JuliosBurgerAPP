package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.ModifierGroup
import com.juliosburger.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetModifierGroupsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productId: String): Flow<List<ModifierGroup>> {
        return productRepository.getModifierGroupsByProduct(productId)
    }
}
