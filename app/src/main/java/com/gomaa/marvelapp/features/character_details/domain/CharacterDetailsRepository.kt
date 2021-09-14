package com.gomaa.marvelapp.features.character_details.domain

import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse

interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(characterId: Int?): ListCharactersResponse?
}