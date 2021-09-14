package com.gomaa.marvelapp.features.character_details.data.remote

import com.gomaa.marvelapp.base.network.EndPointConfig
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
}