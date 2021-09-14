package com.gomaa.marvelapp.features.character_details.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gomaa.marvelapp.features.character_details.domain.CharacterDetailsUseCase
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val useCase: CharacterDetailsUseCase) :
    ViewModel() {

    private val characterDetailsLiveData = MutableLiveData<ListCharactersResponse?>()

    fun listCharacters(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            characterDetailsLiveData.postValue(
                useCase.execute(id)
            )
        }
    }

    fun getCharacterDetailsLiveDate() = characterDetailsLiveData
}