package com.gomaa.marvelapp.features.list_characters.domain.repository

import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse

interface ListCharactersRepository {
    suspend fun getListCharacters(
        limit: Int,
        offset: Int,
        name: String?,
    ): ListCharactersResponse?

}