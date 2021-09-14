package com.gomaa.marvelapp.features.list_characters.presentation.view.listener

import com.gomaa.marvelapp.features.list_characters.domain.model.entity.CharacterEntity

interface OnCharacterClickedListener {
    fun onCharacterClicked(item: CharacterEntity)
}