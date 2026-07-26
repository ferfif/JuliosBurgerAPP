package com.juliosburger.data.database

import com.juliosburger.data.dao.CategoryDao
import com.juliosburger.data.dao.CustomerDao
import com.juliosburger.data.dao.DraftOrderDao
import com.juliosburger.data.dao.DraftOrderItemDao
import com.juliosburger.data.dao.ModifierGroupDao
import com.juliosburger.data.dao.ModifierOptionDao
import com.juliosburger.data.dao.ProductDao

abstract class JuliosBurgerDatabase {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun modifierGroupDao(): ModifierGroupDao
    abstract fun modifierOptionDao(): ModifierOptionDao
    abstract fun draftOrderDao(): DraftOrderDao
    abstract fun draftOrderItemDao(): DraftOrderItemDao
    abstract fun customerDao(): CustomerDao
}
