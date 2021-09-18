package com.gomaa.marvelapp.features.character_details.domain.usecase

import com.gomaa.marvelapp.base.domain.usecase.BaseUseCase
import com.gomaa.marvelapp.features.character_details.domain.reposiory.CharacterDetailsRepository
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterDetailsUseCase @Inject constructor(private val repository: CharacterDetailsRepository) :
    BaseUseCase<Int, ListCharactersResponse?> {
    override suspend fun execute(input: Int): ListCharactersResponse? {
        return repository.getCharacterDetails(input)
    }
}