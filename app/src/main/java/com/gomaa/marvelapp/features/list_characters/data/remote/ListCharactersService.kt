package com.gomaa.marvelapp.features.list_characters.data.remote

import com.gomaa.marvelapp.base.network.EndPointConfig
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ListCharactersService {
    @GET(EndPointConfig.CHARACTERS)
    suspend fun getListCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("ts") ts: String?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("name") name: String?,
    ): ListCharactersResponse?
}