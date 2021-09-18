package com.gomaa.marvelapp.features.character_details.data.remote

import com.gomaa.marvelapp.base.network.EndPointConfig
import com.gomaa.marvelapp.features.character_details.domain.entity.DetailsResponse
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import javax.inject.Inject

class CharacterDetailsRemoteDataSourceImpl @Inject constructor(private val service: CharacterDetailsService) :
    CharacterDetailsRemoteDataSource {
    override suspend fun getCharacterDetails(
        characterId: Int?
    ): ListCharactersResponse? {
        return service.getCharacterDetails(
            characterId,
            EndPointConfig.PUBLIC_KEY,
            EndPointConfig.TIME_STAMP,
            EndPointConfig.HASH
        )
    }

    override suspend fun getSeriesDetails(
        seriesId: Int?
    ): DetailsResponse? {
        return service.getSeriesDetails(
            seriesId,
            EndPointConfig.PUBLIC_KEY,
            EndPointConfig.TIME_STAMP,
            EndPointConfig.HASH
        )
    }

    override suspend fun getEventsDetails(
        eventId: Int?
    ): DetailsResponse? {
        return service.getEventsDetails(
            eventId,
            EndPointConfig.PUBLIC_KEY,
            EndPointConfig.TIME_STAMP,
            EndPointConfig.HASH
        )
    }

    override suspend fun getComicsDetails(
        comicId: Int?
    ): DetailsResponse? {
        return service.getComicsDetails(
            comicId,
            EndPointConfig.PUBLIC_KEY,
            EndPointConfig.TIME_STAMP,
            EndPointConfig.HASH
        )
    }

    override suspend fun getStoriesDetails(storyId: Int?): DetailsResponse? {
        return service.getStoriesDetails(
            storyId,
            EndPointConfig.PUBLIC_KEY,
            EndPointConfig.TIME_STAMP,
            EndPointConfig.HASH
        )
    }
}