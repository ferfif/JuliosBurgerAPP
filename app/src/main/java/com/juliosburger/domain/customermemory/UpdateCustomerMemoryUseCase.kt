package com.juliosburger.domain.customermemory

import kotlinx.coroutines.flow.Flow

class UpdateCustomerMemoryUseCase(private val repository: CustomerMemoryRepository) {
    suspend operator fun invoke(memory: CustomerMemory): Flow<Result<CustomerMemory>> {
        val updated = memory.copy(lastUpdated = java.time.Instant.now())
        return repository.update(updated)
    }
}
