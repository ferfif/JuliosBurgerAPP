package com.juliosburger.di

import com.juliosburger.data.dao.CategoryDao
import com.juliosburger.data.dao.CustomerDao
import com.juliosburger.data.dao.DraftOrderDao
import com.juliosburger.data.dao.DraftOrderItemDao
import com.juliosburger.data.dao.ModifierGroupDao
import com.juliosburger.data.dao.ModifierOptionDao
import com.juliosburger.data.dao.ProductDao
import com.juliosburger.data.database.JuliosBurgerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideCategoryDao(database: JuliosBurgerDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: JuliosBurgerDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideModifierGroupDao(database: JuliosBurgerDatabase): ModifierGroupDao {
        return database.modifierGroupDao()
    }

    @Provides
    @Singleton
    fun provideModifierOptionDao(database: JuliosBurgerDatabase): ModifierOptionDao {
        return database.modifierOptionDao()
    }

    @Provides
    @Singleton
    fun provideDraftOrderDao(database: JuliosBurgerDatabase): DraftOrderDao {
        return database.draftOrderDao()
    }

    @Provides
    @Singleton
    fun provideDraftOrderItemDao(database: JuliosBurgerDatabase): DraftOrderItemDao {
        return database.draftOrderItemDao()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(database: JuliosBurgerDatabase): CustomerDao {
        return database.customerDao()
    }
}
