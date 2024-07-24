package com.example.ilmioristorante.di

import androidx.paging.ExperimentalPagingApi
import com.example.ilmioristorante.data.repository.RepositoryImpl
import com.example.ilmioristorante.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    @ExperimentalPagingApi
    abstract fun provideRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}