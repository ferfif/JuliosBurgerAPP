package com.juliosburger.data.datasource.remote

/**
 * Contrato base para fuentes de datos remotas.
 */
interface RemoteDataSource<T> {
    suspend fun getById(id: String): T?
    suspend fun getAll(): List<T>
    suspend fun create(item: T): T
    suspend fun update(id: String, item: T): T?
    suspend fun delete(id: String)
}
