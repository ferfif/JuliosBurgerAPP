package com.juliosburger.data.dao

import com.juliosburger.data.entity.CustomerEntity

interface CustomerDao {
    suspend fun getAll(): List<CustomerEntity>
    suspend fun getById(id: String): CustomerEntity?
    suspend fun getByPhone(phone: String): CustomerEntity?
    suspend fun insert(customer: CustomerEntity)
    suspend fun insertAll(customers: List<CustomerEntity>)
    suspend fun deleteById(id: String)
}
