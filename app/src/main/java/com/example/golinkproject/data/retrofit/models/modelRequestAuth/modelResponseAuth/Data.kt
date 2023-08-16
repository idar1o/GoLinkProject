package com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseAuth

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("code")
    val code: String

)