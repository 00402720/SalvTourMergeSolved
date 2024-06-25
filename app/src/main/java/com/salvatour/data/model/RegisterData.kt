package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("_id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val hashedPassword: String,
    @SerializedName("telephoneNumber")
    val telephoneNumber: String
)
