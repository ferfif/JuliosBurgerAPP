package com.juliosburger.data.repository

import com.juliosburger.data.dao.CustomerDao
import com.juliosburger.data.mapper.CustomerMapper
import com.juliosburger.domain.model.Customer
import com.juliosburger.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl(
    private val customerDao: CustomerDao
) : CustomerRepository {
    override suspend fun getCustomerByPhone(phone: String): Flow<Customer?> {
        return flow {
            emit(customerDao.getByPhone(phone)?.let { CustomerMapper.mapToDomain(it) })
        }
    }

    override suspend fun saveCustomer(customer: Customer): Flow<Result<Customer>> {
        return flow {
            val entity = CustomerMapper.mapToEntity(customer)
            customerDao.insert(entity)
            val saved = customerDao.getByPhone(customer.phone)
            emit(Result.success(CustomerMapper.mapToDomain(saved!!)))
        }
    }

    override suspend fun updateCustomerPreferences(customer: Customer): Flow<Result<Customer>> {
        return flow {
            val entity = CustomerMapper.mapToEntity(customer)
            customerDao.insert(entity)
            val updated = customerDao.getByPhone(customer.phone)
            emit(Result.success(CustomerMapper.mapToDomain(updated!!)))
        }
    }
}
