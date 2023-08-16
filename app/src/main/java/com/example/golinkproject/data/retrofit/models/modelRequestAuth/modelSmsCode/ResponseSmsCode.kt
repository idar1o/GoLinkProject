package com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelSmsCode

import com.google.gson.annotations.SerializedName

data class ResponseSmsCode(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("code")
    val code: String
)