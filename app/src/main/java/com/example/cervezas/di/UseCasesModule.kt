package com.example.cervezas.di

import com.example.data.repository.ItemRepository
import com.example.usecases.FindItemByIdUseCase
import com.example.usecases.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun getItemsUseCaseProvider(repository: ItemRepository) =
        GetItemsUseCase(repository)

    @Provides
    fun findItemByIdUseCaseProvider(repository: ItemRepository) =
        FindItemByIdUseCase(repository)
}
