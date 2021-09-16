package com.gomaa.marvelapp.features.list_characters.domain.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicsEntity(

    @SerializedName("available") val available : Int?,
    @SerializedName("collectionURI") val collectionURI : String?,
    @SerializedName("items") val items : List<ItemEntity>?,
    @SerializedName("returned") val returned : Int?
):Serializable