package com.gomaa.marvelapp.features.character_details.presentation.view

import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ItemEntity

data class SectionEntity(
    val title:String?,
    val available: Int?,
    val collectionURI: String?,
    val items: List<ItemEntity>?,
    val returned: Int?
)