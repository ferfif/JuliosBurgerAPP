package com.juliosburger.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juliosburger.data.database.JuliosBurgerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideJuliosBurgerDatabase(
        @ApplicationContext context: Context
    ): JuliosBurgerDatabase {
        return Room.databaseBuilder(
            context,
            JuliosBurgerDatabase::class.java,
            "julios_burger_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    db.execSQL("INSERT INTO categories (id, name, description, displayOrder, isActive) VALUES ('00000000-0000-0001-0000-000000000001', 'Hamburguesas', 'Hamburguesas de la casa', 0, 1)")
                    db.execSQL("INSERT INTO categories (id, name, description, displayOrder, isActive) VALUES ('00000000-0000-0002-0000-000000000002', 'Combos', 'Combos completos', 1, 1)")
                    db.execSQL("INSERT INTO categories (id, name, description, displayOrder, isActive) VALUES ('00000000-0000-0003-0000-000000000003', 'Acompañamientos', 'Acompañamientos para complementar tu pedido', 2, 1)")

                    db.execSQL("INSERT INTO products (id, categoryId, name, description, basePrice, imageUrl, isAvailable, displayOrder) VALUES ('10000000-0001-0000-0000-000000000011', '00000000-0000-0001-0000-000000000001', 'Hamburguesa Clásica', 'Hamburguesa clásica de JuliosBurger', 1200.0, NULL, 1, 0)")
                    db.execSQL("INSERT INTO products (id, categoryId, name, description, basePrice, imageUrl, isAvailable, displayOrder) VALUES ('10000000-0001-0000-0000-000000000012', '00000000-0000-0001-0000-000000000001', 'Hamburguesa BBQ', 'Hamburguesa con salsa BBQ', 1400.0, NULL, 1, 1)")
                    db.execSQL("INSERT INTO products (id, categoryId, name, description, basePrice, imageUrl, isAvailable, displayOrder) VALUES ('10000000-0001-0000-0000-000000000021', '00000000-0000-0002-0000-000000000002', 'Combo Clásico', 'Hamburguesa, papas y bebida', 1800.0, NULL, 1, 0)")
                    db.execSQL("INSERT INTO products (id, categoryId, name, description, basePrice, imageUrl, isAvailable, displayOrder) VALUES ('10000000-0001-0000-0000-000000000022', '00000000-0000-0002-0000-000000000002', 'Combo BBQ', 'Hamburguesa BBQ, papas y bebida', 2100.0, NULL, 1, 1)")
                    db.execSQL("INSERT INTO products (id, categoryId, name, description, basePrice, imageUrl, isAvailable, displayOrder) VALUES ('10000000-0001-0000-0000-000000000031', '00000000-0000-0003-0000-000000000003', 'Papas Fritas', 'Papas fritas crujientes', 500.0, NULL, 1, 0)")
                    db.execSQL("INSERT INTO products (id, categoryId, name, description, basePrice, imageUrl, isAvailable, displayOrder) VALUES ('10000000-0001-0000-0000-000000000032', '00000000-0000-0003-0000-000000000003', 'Nachos', 'Nachos con queso', 700.0, NULL, 1, 1)")

                    db.execSQL("INSERT INTO modifier_groups (id, productId, name, minSelection, maxSelection, isRequired, displayOrder) VALUES ('20000000-0001-0000-0000-000000000011', '10000000-0001-0000-0000-000000000011', 'Tipo de queso', 1, 1, 1, 0)")
                    db.execSQL("INSERT INTO modifier_groups (id, productId, name, minSelection, maxSelection, isRequired, displayOrder) VALUES ('20000000-0001-0000-0000-000000000012', '10000000-0001-0000-0000-000000000011', 'Extras', 0, 3, 0, 1)")

                    db.execSQL("INSERT INTO modifier_options (id, modifierGroupId, name, priceAdjustment, isDefault, isActive) VALUES ('30000000-0001-0000-0000-000000000001', '20000000-0001-0000-0000-000000000011', 'Cheddar', 200.0, 1, 1)")
                    db.execSQL("INSERT INTO modifier_options (id, modifierGroupId, name, priceAdjustment, isDefault, isActive) VALUES ('30000000-0001-0000-0000-000000000002', '20000000-0001-0000-0000-000000000011', 'Manchego', 300.0, 0, 1)")
                    db.execSQL("INSERT INTO modifier_options (id, modifierGroupId, name, priceAdjustment, isDefault, isActive) VALUES ('30000000-0001-0000-0000-000000000003', '20000000-0001-0000-0000-000000000012', 'Tocino', 400.0, 0, 1)")
                    db.execSQL("INSERT INTO modifier_options (id, modifierGroupId, name, priceAdjustment, isDefault, isActive) VALUES ('30000000-0001-0000-0000-000000000004', '20000000-0001-0000-0000-000000000012', 'Huevo', 300.0, 1, 1)")
                }
            })
            .build()
    }
}
