package com.example.cervezas.di

import com.example.data.repository.ItemRepository
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun repositoryProvider(remoteDataSource: RemoteDataSource) =
        ItemRepository(remoteDataSource)
}
