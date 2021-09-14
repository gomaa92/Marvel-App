package com.gomaa.marvelapp.features.list_characters.data.remote

import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse

interface ListCharactersDataSource {
    suspend fun getListCharacters(
        limit: Int,
        offset: Int,
        name: String?,
    ): ListCharactersResponse?
}