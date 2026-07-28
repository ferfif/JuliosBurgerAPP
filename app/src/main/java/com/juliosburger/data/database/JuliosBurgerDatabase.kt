package com.juliosburger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juliosburger.data.dao.CategoryDao
import com.juliosburger.data.dao.CustomerDao
import com.juliosburger.data.dao.DraftOrderDao
import com.juliosburger.data.dao.DraftOrderItemDao
import com.juliosburger.data.dao.ModifierGroupDao
import com.juliosburger.data.dao.ModifierOptionDao
import com.juliosburger.data.dao.ProductDao
import com.juliosburger.data.entity.CategoryEntity
import com.juliosburger.data.entity.CustomerEntity
import com.juliosburger.data.entity.DraftOrderEntity
import com.juliosburger.data.entity.DraftOrderItemEntity
import com.juliosburger.data.entity.ModifierGroupEntity
import com.juliosburger.data.entity.ModifierOptionEntity
import com.juliosburger.data.entity.ProductEntity

@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        ModifierGroupEntity::class,
        ModifierOptionEntity::class,
        DraftOrderEntity::class,
        DraftOrderItemEntity::class,
        CustomerEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JuliosBurgerDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun modifierGroupDao(): ModifierGroupDao
    abstract fun modifierOptionDao(): ModifierOptionDao
    abstract fun draftOrderDao(): DraftOrderDao
    abstract fun draftOrderItemDao(): DraftOrderItemDao
    abstract fun customerDao(): CustomerDao
}
