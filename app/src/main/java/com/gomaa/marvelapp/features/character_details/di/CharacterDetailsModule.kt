package com.gomaa.marvelapp.features.character_details.di

import com.gomaa.marvelapp.features.character_details.data.remote.CharacterDetailsRemoteDataSource
import com.gomaa.marvelapp.features.character_details.data.remote.CharacterDetailsRemoteDataSourceImpl
import com.gomaa.marvelapp.features.character_details.data.repository.CharacterDetailsRepositoryImpl
import com.gomaa.marvelapp.features.character_details.domain.CharacterDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class CharacterDetailsModule {
    @Binds
    abstract fun provideCharacterDetailsRepository(repository: CharacterDetailsRepositoryImpl): CharacterDetailsRepository

    @Binds
    abstract fun provideCharacterDetailsRemoteDataSourceModel(
        remoteDataSource: CharacterDetailsRemoteDataSourceImpl
    ): CharacterDetailsRemoteDataSource
}