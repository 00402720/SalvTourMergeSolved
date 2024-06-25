package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("_id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("whatsappLink")
    val whatsappLink: String,
    @SerializedName("mapLink")
    val mapLink: String,
    @SerializedName("user")
    val user: String
)