package com.juliosburger.di

import com.juliosburger.data.dao.CategoryDao
import com.juliosburger.data.dao.CustomerDao
import com.juliosburger.data.dao.DraftOrderDao
import com.juliosburger.data.dao.DraftOrderItemDao
import com.juliosburger.data.dao.ModifierGroupDao
import com.juliosburger.data.dao.ModifierOptionDao
import com.juliosburger.data.dao.ProductDao
import com.juliosburger.data.mapper.DraftOrderItemMapper
import com.juliosburger.data.repository.CustomerRepositoryImpl
import com.juliosburger.data.repository.OrderRepositoryImpl
import com.juliosburger.data.repository.ProductRepositoryImpl
import com.juliosburger.domain.repository.CustomerRepository
import com.juliosburger.domain.repository.OrderRepository
import com.juliosburger.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        categoryDao: CategoryDao,
        productDao: ProductDao,
        modifierGroupDao: ModifierGroupDao,
        modifierOptionDao: ModifierOptionDao
    ): ProductRepository {
        return ProductRepositoryImpl(categoryDao, productDao, modifierGroupDao, modifierOptionDao)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(
        draftOrderDao: DraftOrderDao,
        draftOrderItemDao: DraftOrderItemDao,
        draftOrderItemMapper: DraftOrderItemMapper
    ): OrderRepository {
        return OrderRepositoryImpl(draftOrderDao, draftOrderItemDao, draftOrderItemMapper)
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(
        customerDao: CustomerDao
    ): CustomerRepository {
        return CustomerRepositoryImpl(customerDao)
    }
}
