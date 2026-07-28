package com.juliosburger.di

import com.juliosburger.data.serialization.KotlinxSnapshotSerializer
import com.juliosburger.data.serialization.SnapshotSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SerializationModule {

    @Provides
    @Singleton
    fun provideSnapshotSerializer(): SnapshotSerializer {
        return KotlinxSnapshotSerializer()
    }
}
