package com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseAuth

import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @SerializedName("data")
    val `data`: Data
)