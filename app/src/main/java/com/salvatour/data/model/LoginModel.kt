package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("identifier")
    var identifier: String,

    @SerializedName("password")
    var password: String
)