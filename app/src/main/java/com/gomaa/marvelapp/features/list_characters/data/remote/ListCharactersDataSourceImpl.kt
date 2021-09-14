package com.gomaa.marvelapp.features.list_characters.data.remote

import com.gomaa.marvelapp.base.network.EndPointConfig
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import javax.inject.Inject

class ListCharactersDataSourceImpl @Inject constructor(private val service: ListCharactersService) :
    ListCharactersDataSource {
    override suspend fun getListCharacters(
        limit: Int,
        offset: Int,
        name: String?
    ): ListCharactersResponse? {
        return service.getListCharacters(
            limit,
            offset,
            EndPointConfig.TIME_STAMP,
            EndPointConfig.PUBLIC_KEY,
            EndPointConfig.HASH,
            name
        )
    }
}