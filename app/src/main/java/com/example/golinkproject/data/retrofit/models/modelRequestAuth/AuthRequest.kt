package com.example.golinkproject.data.retrofit.models.modelRequestAuth

import com.google.gson.annotations.SerializedName
data class AuthRequest(
    @SerializedName("login")
    var regusername: String,
    @SerializedName("password")
    var regpassword: String,
    @SerializedName("phone")
    var regnumber: String,
    @SerializedName("code")
    var code: String
)

