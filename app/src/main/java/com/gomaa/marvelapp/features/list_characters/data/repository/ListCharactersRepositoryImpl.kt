package com.gomaa.marvelapp.features.list_characters.data.repository

import com.gomaa.marvelapp.features.list_characters.data.remote.ListCharactersDataSource
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import com.gomaa.marvelapp.features.list_characters.domain.repository.ListCharactersRepository
import javax.inject.Inject

class ListCharactersRepositoryImpl @Inject constructor(private val dataSource: ListCharactersDataSource) :
    ListCharactersRepository {
    override suspend fun getListCharacters(
        limit: Int?,
        offset: Int?,
        name: String?
    ): ListCharactersResponse? {
        return dataSource.getListCharacters(limit, offset, name)
    }
}