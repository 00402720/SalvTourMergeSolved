package com.salvatour.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponseSuccessful(
    @SerializedName("")
    val tour : String
)
data class ApiResponseError(
    @SerializedName("")
    val message : String
)