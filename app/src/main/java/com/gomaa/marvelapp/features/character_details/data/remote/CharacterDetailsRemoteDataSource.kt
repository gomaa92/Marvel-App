package com.gomaa.marvelapp.features.character_details.data.remote

import com.gomaa.marvelapp.features.character_details.domain.entity.DetailsResponse
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse

interface CharacterDetailsRemoteDataSource {
    suspend fun getCharacterDetails(
        characterId: Int?
    ): ListCharactersResponse?

    suspend fun getSeriesDetails(
        seriesId: Int?
    ): DetailsResponse?

    suspend fun getEventsDetails(
        eventId: Int?
    ): DetailsResponse?

    suspend fun getComicsDetails(
        comicId: Int?
    ): DetailsResponse?
    suspend fun getStoriesDetails(
        storyId: Int?
    ): DetailsResponse?
}