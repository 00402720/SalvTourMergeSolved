package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    var token: String,
    val user: UserModel
)