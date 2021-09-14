package com.gomaa.marvelapp.features.list_characters.di

import com.gomaa.marvelapp.features.list_characters.data.remote.ListCharactersDataSource
import com.gomaa.marvelapp.features.list_characters.data.remote.ListCharactersDataSourceImpl
import com.gomaa.marvelapp.features.list_characters.data.repository.ListCharactersRepositoryImpl
import com.gomaa.marvelapp.features.list_characters.domain.repository.ListCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ListCharactersModule {
    @Binds
    abstract fun provideListCharactersRepository(repository: ListCharactersRepositoryImpl): ListCharactersRepository

    @Binds
    abstract fun provideListCharactersRemoteDataSourceModel(
        remoteDataSource: ListCharactersDataSourceImpl
    ): ListCharactersDataSource
}