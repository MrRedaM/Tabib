package com.redapps.tabib.network

import com.redapps.tabib.model.Doctor
import com.redapps.tabib.model.User
import com.redapps.tabib.model.UserLogin
import retrofit2.Call
import retrofit2.http.*

interface AuthApiService {

    @POST("auth/login")
    @FormUrlEncoded
    fun login(@Field("phone") phone: String, @Field("password") pass: String): Call<String>
}