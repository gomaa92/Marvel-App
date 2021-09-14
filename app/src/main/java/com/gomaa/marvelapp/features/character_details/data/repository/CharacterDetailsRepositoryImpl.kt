package com.gomaa.marvelapp.features.character_details.data.repository

import com.gomaa.marvelapp.features.character_details.data.remote.CharacterDetailsRemoteDataSource
import com.gomaa.marvelapp.features.character_details.domain.CharacterDetailsRepository
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(private val remoteDataSource: CharacterDetailsRemoteDataSource) :
    CharacterDetailsRepository {
    override suspend fun getCharacterDetails(characterId: Int?): ListCharactersResponse? {
        return remoteDataSource.getCharacterDetails(characterId)
    }
}