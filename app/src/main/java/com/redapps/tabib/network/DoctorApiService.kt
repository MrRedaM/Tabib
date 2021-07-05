package com.redapps.tabib.network

import com.redapps.tabib.model.Booking
import com.redapps.tabib.model.BookingFetch
import com.redapps.tabib.model.Doctor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DoctorApiService {

    @GET("doctor")
    fun getDoctors(): Call<List<Doctor>>

    @POST("appointment")
    fun getAppointmentsByDocAndDate(@Body bookingFetch: BookingFetch): Call<List<Booking>>

}