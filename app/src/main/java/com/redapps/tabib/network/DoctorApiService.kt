package com.redapps.tabib.network

import com.redapps.tabib.model.Doctor
import retrofit2.Call
import retrofit2.http.GET

interface DoctorApiService {

    @GET("doctor")
    fun getDoctors(): Call<List<Doctor>>
}