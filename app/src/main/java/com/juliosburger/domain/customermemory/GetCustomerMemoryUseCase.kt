package com.juliosburger.domain.customermemory

import kotlinx.coroutines.flow.Flow

/**
 * Recupera la memoria de un cliente a partir de su número telefónico.
 *
 * @property repository Repositorio de acceso a la memoria del cliente.
 */
class GetCustomerMemoryUseCase(private val repository: CustomerMemoryRepository) {
    suspend operator fun invoke(phone: String): Flow<CustomerMemory?> = repository.getByPhone(phone)
}
