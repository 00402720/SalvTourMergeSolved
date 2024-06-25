package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (
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
    @SerializedName("hashedPassword")
    val hashedPassword: String,
    @SerializedName("telephoneNumber")
    val telephoneNumber: String,
    @SerializedName("salt")
    val salt: String,
    @SerializedName("tokens")
    val tokens: String,
    @SerializedName("roles")
    val roles: String,
    @SerializedName("savedPosts")
    val savedPosts: String
)
