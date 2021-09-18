package com.gomaa.marvelapp.features.character_details.domain.usecase

import com.gomaa.marvelapp.base.domain.usecase.BaseUseCase
import com.gomaa.marvelapp.features.character_details.domain.entity.DetailsResponse
import com.gomaa.marvelapp.features.character_details.domain.reposiory.CharacterDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetComicDetailsUseCase @Inject constructor(private val repository: CharacterDetailsRepository) :
    BaseUseCase<Int, DetailsResponse?> {
    override suspend fun execute(input: Int): DetailsResponse? {
        return repository.getComicsDetails(input)
    }
}