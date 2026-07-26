package com.juliosburger.data.datasource.local

/**
 * Contrato base para fuentes de datos locales.
 */
interface LocalDataSource<T> {
    suspend fun getById(id: String): T?
    suspend fun getAll(): List<T>
    suspend fun save(item: T): T
    suspend fun update(item: T): T
    suspend fun delete(id: String)
}
