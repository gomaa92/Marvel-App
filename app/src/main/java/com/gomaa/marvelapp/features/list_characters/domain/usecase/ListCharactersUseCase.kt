package com.gomaa.marvelapp.features.list_characters.domain.usecase

import com.gomaa.marvelapp.base.domain.usecase.BaseUseCase
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import com.gomaa.marvelapp.features.list_characters.domain.model.params.ListCharactersParams
import com.gomaa.marvelapp.features.list_characters.domain.repository.ListCharactersRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ListCharactersUseCase @Inject constructor(private val repository: ListCharactersRepository) :
    BaseUseCase<ListCharactersParams, ListCharactersResponse?> {
    override suspend fun execute(input: ListCharactersParams): ListCharactersResponse? {
        return repository.getListCharacters(input.limit, input.offset, input.name)
    }

}