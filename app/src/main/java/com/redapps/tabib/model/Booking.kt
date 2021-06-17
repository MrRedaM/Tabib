package com.redapps.tabib.model

import java.time.LocalDate
import java.util.*

data class Booking(
    var booked : Boolean,
    var startDate : Date,
    var endDate : Date
)
