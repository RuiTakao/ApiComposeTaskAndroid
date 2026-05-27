package com.takaobrog.core.di

import com.takaobrog.core.util.TimeProvider
import com.takaobrog.core.util.TimeProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeProviderBindModule {

    @Binds
    @Singleton
    abstract fun bindTimeProvider(
        impl: TimeProviderImpl
    ): TimeProvider
}