package com.juliosburger.di

import com.juliosburger.domain.repository.ProductRepository
import com.juliosburger.domain.usecase.GetCategoriesUseCase
import com.juliosburger.domain.usecase.GetModifierGroupsUseCase
import com.juliosburger.domain.usecase.GetModifierOptionsUseCase
import com.juliosburger.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(
        productRepository: ProductRepository
    ): GetCategoriesUseCase {
        return GetCategoriesUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        productRepository: ProductRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetModifierGroupsUseCase(
        productRepository: ProductRepository
    ): GetModifierGroupsUseCase {
        return GetModifierGroupsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetModifierOptionsUseCase(
        productRepository: ProductRepository
    ): GetModifierOptionsUseCase {
        return GetModifierOptionsUseCase(productRepository)
    }
}
