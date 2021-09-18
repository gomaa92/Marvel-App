package com.gomaa.marvelapp.features.character_details.data.repository

import com.gomaa.marvelapp.features.character_details.data.remote.CharacterDetailsRemoteDataSource
import com.gomaa.marvelapp.features.character_details.domain.reposiory.CharacterDetailsRepository
import com.gomaa.marvelapp.features.character_details.domain.entity.DetailsResponse
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(private val remoteDataSource: CharacterDetailsRemoteDataSource) :
    CharacterDetailsRepository {
    override suspend fun getCharacterDetails(characterId: Int?): ListCharactersResponse? {
        return remoteDataSource.getCharacterDetails(characterId)
    }

    override suspend fun getSeriesDetails(seriesId: Int?): DetailsResponse? {
        return remoteDataSource.getSeriesDetails(seriesId)
    }

    override suspend fun getEventsDetails(eventId: Int?): DetailsResponse? {
        return remoteDataSource.getEventsDetails(eventId)
    }

    override suspend fun getComicsDetails(comicId: Int?): DetailsResponse? {
        return remoteDataSource.getComicsDetails(comicId)
    }

    override suspend fun getStoriesDetails(storyId: Int?): DetailsResponse? {
        return remoteDataSource.getStoriesDetails(storyId)
    }
}