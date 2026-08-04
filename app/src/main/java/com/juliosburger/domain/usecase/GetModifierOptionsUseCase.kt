package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetModifierOptionsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(groupId: String): Flow<List<ModifierOption>> {
        return productRepository.getModifierOptionsByGroup(groupId)
    }
}
