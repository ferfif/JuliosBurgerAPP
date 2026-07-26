package com.juliosburger.domain.customermemory

import kotlinx.coroutines.flow.Flow

/**
 * Puerto de salida para acceder y modificar la memoria de clientes.
 */
interface CustomerMemoryRepository {
    fun getByPhone(phone: String): Flow<CustomerMemory?>
    suspend fun save(memory: CustomerMemory): Flow<Result<CustomerMemory>>
    suspend fun update(memory: CustomerMemory): Flow<Result<CustomerMemory>>
}
