package com.juliosburger.data.dao

import com.juliosburger.data.entity.ProductEntity

interface ProductDao {
    suspend fun getAll(): List<ProductEntity>
    suspend fun getById(id: String): ProductEntity?
    suspend fun getByCategory(categoryId: String): List<ProductEntity>
    suspend fun insert(product: ProductEntity)
    suspend fun insertAll(products: List<ProductEntity>)
    suspend fun deleteById(id: String)
}
