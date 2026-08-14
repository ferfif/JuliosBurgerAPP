package com.juliosburger.di

import com.juliosburger.data.mapper.DraftOrderItemMapper
import com.juliosburger.data.serialization.SnapshotSerializer
import com.juliosburger.domain.repository.OrderRepository
import com.juliosburger.domain.repository.ProductRepository
import com.juliosburger.domain.usecase.AcceptDraftOrderUseCase
import com.juliosburger.domain.usecase.CompleteOrderUseCase
import com.juliosburger.domain.usecase.StartCookingUseCase
import com.juliosburger.domain.usecase.ConfirmDraftOrderUseCase
import com.juliosburger.domain.usecase.CreateDraftOrderUseCase
import com.juliosburger.domain.usecase.GetCategoriesUseCase
import com.juliosburger.domain.usecase.GetDraftOrdersUseCase
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

    @Provides
    @Singleton
    fun provideCreateDraftOrderUseCase(
        orderRepository: OrderRepository
    ): CreateDraftOrderUseCase {
        return CreateDraftOrderUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideGetDraftOrdersUseCase(
        orderRepository: OrderRepository
    ): GetDraftOrdersUseCase {
        return GetDraftOrdersUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideConfirmDraftOrderUseCase(
        orderRepository: OrderRepository
    ): ConfirmDraftOrderUseCase {
        return ConfirmDraftOrderUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideAcceptDraftOrderUseCase(
        orderRepository: OrderRepository
    ): AcceptDraftOrderUseCase {
        return AcceptDraftOrderUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideStartCookingUseCase(
        orderRepository: OrderRepository
    ): StartCookingUseCase {
        return StartCookingUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideCompleteOrderUseCase(
        orderRepository: OrderRepository
    ): CompleteOrderUseCase {
        return CompleteOrderUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideDraftOrderItemMapper(
        snapshotSerializer: SnapshotSerializer
    ): DraftOrderItemMapper {
        return DraftOrderItemMapper(snapshotSerializer)
    }
}
