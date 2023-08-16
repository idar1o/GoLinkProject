package com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseCheck

import com.google.gson.annotations.SerializedName

data class ResponseNumberCheck (
    @SerializedName("data")
    val data: NumData
)

data class NumData (
    @SerializedName("response")
    val response: Boolean,
    @SerializedName("status")
    val status: String
    )
