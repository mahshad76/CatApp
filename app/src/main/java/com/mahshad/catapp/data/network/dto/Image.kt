package com.mahshad.catapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id") val id: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?
)