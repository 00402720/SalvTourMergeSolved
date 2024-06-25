package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("token")
    var token: String
)