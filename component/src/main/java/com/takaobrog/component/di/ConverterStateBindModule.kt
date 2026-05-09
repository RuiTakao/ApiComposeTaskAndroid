package com.takaobrog.component.di

import com.takaobrog.component.model.ConverterState
import com.takaobrog.component.model.ConverterStateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConverterStateBindModule {

    @Binds
    @Singleton
    abstract fun bindConverterState(
        impl: ConverterStateImpl,
    ): ConverterState
}