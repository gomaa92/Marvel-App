package com.gomaa.marvelapp.features.list_characters.domain.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemEntity(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
):Serializable