package com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseCheck

import com.google.gson.annotations.SerializedName

data class ResponseLoginCheck(
    @SerializedName("data")
    val data: LogData
)

data class LogData (
    @SerializedName("response")
    val response: Boolean,
    @SerializedName("status")
    val status: String
)