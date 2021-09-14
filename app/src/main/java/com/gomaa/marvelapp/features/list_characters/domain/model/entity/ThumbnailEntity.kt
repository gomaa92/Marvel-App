package com.gomaa.marvelapp.features.list_characters.domain.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ThumbnailEntity(
    @SerializedName("path") var path: String,
    @SerializedName("extension") val extension: String
):Serializable