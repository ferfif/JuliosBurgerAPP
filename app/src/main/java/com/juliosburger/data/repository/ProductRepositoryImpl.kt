package com.juliosburger.data.repository

import com.juliosburger.data.dao.CategoryDao
import com.juliosburger.data.dao.ModifierGroupDao
import com.juliosburger.data.dao.ModifierOptionDao
import com.juliosburger.data.dao.ProductDao
import com.juliosburger.data.mapper.CategoryMapper
import com.juliosburger.data.mapper.ModifierGroupMapper
import com.juliosburger.data.mapper.ModifierOptionMapper
import com.juliosburger.data.mapper.ProductMapper
import com.juliosburger.domain.model.Category
import com.juliosburger.domain.model.ModifierGroup
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.model.Product
import com.juliosburger.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val modifierGroupDao: ModifierGroupDao,
    private val modifierOptionDao: ModifierOptionDao
) : ProductRepository {
    override suspend fun getCategories(): Flow<List<Category>> {
        return flow {
            emit(categoryDao.getAll().map { CategoryMapper.mapToDomain(it) })
        }
    }

    override suspend fun getProductsByCategory(categoryId: String): Flow<List<Product>> {
        return flow {
            emit(productDao.getByCategory(categoryId).map { ProductMapper.mapToDomain(it) })
        }
    }

    override suspend fun getModifierGroupsByProduct(productId: String): Flow<List<ModifierGroup>> {
        return flow {
            emit(modifierGroupDao.getByProduct(productId).map { ModifierGroupMapper.mapToDomain(it) })
        }
    }

    override suspend fun getModifierOptionsByGroup(groupId: String): Flow<List<ModifierOption>> {
        return flow {
            emit(modifierOptionDao.getByGroup(groupId).map { ModifierOptionMapper.mapToDomain(it) })
        }
    }
}
