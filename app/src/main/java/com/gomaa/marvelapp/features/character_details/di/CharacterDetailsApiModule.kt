package com.gomaa.marvelapp.features.character_details.di

import com.gomaa.marvelapp.features.character_details.data.remote.CharacterDetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class CharacterDetailsApiModule {
    @Provides
    fun provideCharacterDetailsApi(retrofit: Retrofit): CharacterDetailsService {
        return retrofit.create(CharacterDetailsService::class.java)
    }
}