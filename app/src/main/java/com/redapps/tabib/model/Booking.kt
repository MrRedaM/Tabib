package com.redapps.tabib.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

data class Booking(
    var booked : Boolean,
    @SerializedName("date") var startDate : Date,
    var endDate : Date
)
