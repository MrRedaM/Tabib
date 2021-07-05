package com.redapps.tabib.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class Treatment(
    @SerializedName("idTreatment") val idTreatment: Int,
    @SerializedName("idDoc") val idDoc: Int,
    @SerializedName("idPatient") val idPatient: Int,
    @SerializedName("durationTreatment") val durationTreatment: String,
    @SerializedName("dateStartTreatment") val dateStartTreatment: Date,
    @SerializedName("medicamentList") var medicamentList: List<Medicament>
)
