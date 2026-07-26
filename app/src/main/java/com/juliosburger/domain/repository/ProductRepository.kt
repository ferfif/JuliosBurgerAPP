package com.juliosburger.domain.repository

import com.juliosburger.domain.model.Category
import com.juliosburger.domain.model.ModifierGroup
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Puerto de salida para acceder a los datos del catálogo.
 *
 * Define el contrato que debe cumplir cualquier fuente de datos (local o remota)
 * para proporcionar información del menú, productos, categorías y modificadores.
 */
interface ProductRepository {
    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getProductsByCategory(categoryId: String): Flow<List<Product>>
    suspend fun getModifierGroupsByProduct(productId: String): Flow<List<ModifierGroup>>
    suspend fun getModifierOptionsByGroup(groupId: String): Flow<List<ModifierOption>>
}
