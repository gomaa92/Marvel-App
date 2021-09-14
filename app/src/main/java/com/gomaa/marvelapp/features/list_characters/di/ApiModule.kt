package com.gomaa.marvelapp.features.list_characters.di

import com.gomaa.marvelapp.features.list_characters.data.remote.ListCharactersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class ApiModule {
    @Provides
    fun provideListCharactersApi(retrofit: Retrofit): ListCharactersService {
        return retrofit.create(ListCharactersService::class.java)
    }
}