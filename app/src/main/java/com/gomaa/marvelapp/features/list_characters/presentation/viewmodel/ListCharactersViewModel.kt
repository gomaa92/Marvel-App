package com.gomaa.marvelapp.features.list_characters.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gomaa.marvelapp.base.util.LIMIT_PAGE_COUNT
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import com.gomaa.marvelapp.features.list_characters.domain.model.params.ListCharactersParams
import com.gomaa.marvelapp.features.list_characters.domain.usecase.ListCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(private val useCase: ListCharactersUseCase) :
    ViewModel() {
    private val listCharactersLiveData = MutableLiveData<ListCharactersResponse?>()
    private val listSearchCharactersLiveData = MutableLiveData<ListCharactersResponse?>()
    private var offset = -1
    fun setOffset(offset: Int) {
        this.offset = offset

    }

    fun listCharacters(name: String? = null, isSearch: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isSearch) {
                listSearchCharactersLiveData.postValue(
                    useCase.execute(ListCharactersParams(null, null, name))
                )
                return@launch
            }
            listCharactersLiveData.postValue(
                useCase.execute(ListCharactersParams(LIMIT_PAGE_COUNT, ++offset, name))
            )
        }
    }

    fun getListCharactersLiveDate() = listCharactersLiveData
    fun getListSearchCharactersLiveDate() = listSearchCharactersLiveData
}