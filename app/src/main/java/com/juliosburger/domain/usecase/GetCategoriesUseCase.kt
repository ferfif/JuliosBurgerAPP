package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.Category
import com.juliosburger.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): Flow<List<Category>> {
        return productRepository.getCategories()
    }
}
