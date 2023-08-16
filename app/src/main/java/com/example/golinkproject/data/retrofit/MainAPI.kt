package com.example.golinkproject.data.retrofit

import com.example.golinkproject.data.retrofit.models.modelRequestAuth.AuthRequest
import com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseAuth.ResponseUser
import com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseCheck.ResponseLoginCheck
import com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelResponseCheck.ResponseNumberCheck
import com.example.golinkproject.data.retrofit.models.modelRequestAuth.modelSmsCode.ResponseSmsCode
import retrofit2.http.*

interface MainAPI {

    @GET("/check_vacant_number/{number}")
    suspend fun checkNumber(@Path("number") number: String): ResponseNumberCheck

    @GET("/check_vacant_login/{login}")
    suspend fun checkLogin(@Path("login") login: String): ResponseLoginCheck

    @GET("/send_message/{phone_number}")
    suspend fun smsCode(@Path("phone_number") phone_number: String): ResponseSmsCode

    @POST("/check_code_right")
    suspend fun smsCodeCheckingAuth(@Body authRequest: AuthRequest): ResponseUser

}