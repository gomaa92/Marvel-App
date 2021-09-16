package com.gomaa.marvelapp.features.list_characters.domain.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnail: ThumbnailEntity,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("comics") val comics: ComicsEntity?,
    @SerializedName("series") val series: SeriesEntity?,
    @SerializedName("stories") val stories: StoriesEntity?,
    @SerializedName("events") val events: EventsEntity?,
    @SerializedName("urls") val urls: List<UrlsEntity>,
    var searchText: String = ""
) : Serializable