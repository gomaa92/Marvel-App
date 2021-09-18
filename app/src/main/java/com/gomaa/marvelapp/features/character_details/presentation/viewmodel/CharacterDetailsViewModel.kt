package com.gomaa.marvelapp.features.character_details.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gomaa.marvelapp.features.character_details.domain.entity.DetailsResponse
import com.gomaa.marvelapp.features.character_details.domain.usecase.*
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ListCharactersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val useCase: CharacterDetailsUseCase,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase,
    private var getEventDetailsUseCase: GetEventDetailsUseCase,
    private var getComicDetailsUseCase: GetComicDetailsUseCase,
    private var getStoriesDetailsUseCase: GetStoriesDetailsUseCase
) :
    ViewModel() {
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("CharacterDetailsView", "got:  $exception")
        println("CoroutineExceptionHandler got $exception")
    }

    private val characterDetailsLiveData = MutableLiveData<ListCharactersResponse?>()
    private val itemComicsDetails = MutableLiveData<List<DetailsResponse?>>()
    private val itemEventsDetails = MutableLiveData<List<DetailsResponse?>>()
    private val itemSeriesDetails = MutableLiveData<List<DetailsResponse?>>()
    private val itemStoriesDetails = MutableLiveData<List<DetailsResponse?>>()

    fun listCharacters(id: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            characterDetailsLiveData.postValue(
                useCase.execute(id)
            )
        }
    }

    fun getComicsItemDetails(items: ArrayList<String>) {
        CoroutineScope(Dispatchers.IO + handler).launch {

            val results = items.map {
                async {
                    getComicDetailsUseCase.execute(it.toInt())
                }
            }.awaitAll()
            itemComicsDetails.postValue(results)
        }

    }

    fun getEventsItemDetails(items: ArrayList<String>) {
        CoroutineScope(Dispatchers.IO + handler).launch {

            val results = items.map {
                async {
                    getEventDetailsUseCase.execute(it.toInt())
                }
            }.awaitAll()
            itemEventsDetails.postValue(results)
        }

    }

    fun getSeriesItemDetails(items: ArrayList<String>) {
        CoroutineScope(Dispatchers.IO + handler).launch {

            val results = items.map {
                async {
                    getSeriesDetailsUseCase.execute(it.toInt())
                }
            }.awaitAll()
            itemSeriesDetails.postValue(results)
        }

    }

    fun getStoriesItemDetails(items: ArrayList<String>) {
        CoroutineScope(Dispatchers.IO + handler).launch {

            val results = items.map {
                async {
                    getStoriesDetailsUseCase.execute(it.toInt())
                }
            }.awaitAll()
            itemStoriesDetails.postValue(results)
        }

    }

    fun getComicsDetailsLiveDate() = itemComicsDetails
    fun getEventsDetailsLiveDate() = itemEventsDetails
    fun getSeriesDetailsLiveDate() = itemSeriesDetails
    fun getStoriesDetailsLiveDate() = itemStoriesDetails

    fun getCharacterDetailsLiveDate() = characterDetailsLiveData
}