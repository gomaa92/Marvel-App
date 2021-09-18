package com.gomaa.marvelapp.features.character_details.data.remote

import com.gomaa.marvelapp.base.network.EndPointConfig
import com.gomaa.marvelapp.features.character_details.domain.entity.DetailsResponse
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterDetailsService {
    @GET(EndPointConfig.CHARACTERS_DETAILS)
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int?,
        @Query("apikey") apiKey: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
    ): ListCharactersResponse?

    @GET(EndPointConfig.SERIES_DETAILS)
    suspend fun getSeriesDetails(
        @Path("seriesId") seriesId: Int?,
        @Query("apikey") apiKey: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
    ): DetailsResponse?

    @GET(EndPointConfig.EVENT_DETAILS)
    suspend fun getEventsDetails(
        @Path("eventId") eventId: Int?,
        @Query("apikey") apiKey: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
    ): DetailsResponse?

    @GET(EndPointConfig.COMIC_DETAILS)
    suspend fun getComicsDetails(
        @Path("comicId") comicId: Int?,
        @Query("apikey") apiKey: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
    ): DetailsResponse?

    @GET(EndPointConfig.STORY_DETAILS)
    suspend fun getStoriesDetails(
        @Path("storyId") storyId: Int?,
        @Query("apikey") apiKey: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
    ): DetailsResponse?
}