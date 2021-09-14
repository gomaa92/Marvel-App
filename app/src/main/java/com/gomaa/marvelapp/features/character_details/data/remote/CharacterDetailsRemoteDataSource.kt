package com.gomaa.marvelapp.features.character_details.data.remote

import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse

interface CharacterDetailsRemoteDataSource {
    suspend fun getCharacterDetails(
        characterId: Int?
    ): ListCharactersResponse?
}