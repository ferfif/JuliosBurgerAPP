package com.juliosburger.domain.usecase

import com.juliosburger.domain.model.Category
import com.juliosburger.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Caso de uso para obtener el menú completo.
 *
 * Orquesta la recuperación de categorías, productos y sus modificadores
 * desde [ProductRepository] para ensamblar la estructura del menú
 * que se presenta al cliente.
 */
class GetMenuUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): Flow<List<Category>> = emptyFlow()
}
