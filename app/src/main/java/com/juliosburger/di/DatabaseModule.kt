package com.juliosburger.di

import android.content.Context
import androidx.room.Room
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
        ).build()
    }
}
