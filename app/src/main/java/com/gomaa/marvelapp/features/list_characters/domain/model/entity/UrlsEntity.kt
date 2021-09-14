package com.gomaa.marvelapp.features.list_characters.domain.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UrlsEntity(
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
): Serializable