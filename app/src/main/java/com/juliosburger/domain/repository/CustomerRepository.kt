package com.juliosburger.domain.repository

import com.juliosburger.domain.model.Customer
import kotlinx.coroutines.flow.Flow

/**
 * Puerto de salida para gestionar la memoria persistente de clientes.
 *
 * Proporciona acceso a las preferencias y el historial de compras
 * necesarios para la personalización segura de la experiencia conversacional.
 */
interface CustomerRepository {
    suspend fun getCustomerByPhone(phone: String): Flow<Customer?>
    suspend fun saveCustomer(customer: Customer): Flow<Result<Customer>>
    suspend fun updateCustomerPreferences(customer: Customer): Flow<Result<Customer>>
}
