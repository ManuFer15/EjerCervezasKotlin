package com.example.cervezas.di

import com.example.cervezas.server.ServerDataSource
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteDataSource = ServerDataSource()
}
