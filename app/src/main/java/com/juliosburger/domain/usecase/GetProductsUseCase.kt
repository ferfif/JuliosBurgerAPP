package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.Product
import com.juliosburger.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(categoryId: String): Flow<List<Product>> {
        return productRepository.getProductsByCategory(categoryId)
    }
}
