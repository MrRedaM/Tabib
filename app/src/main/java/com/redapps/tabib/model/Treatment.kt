package com.redapps.tabib.model

import java.util.*
import kotlin.collections.ArrayList

data class Treatment(
    val idTreatment: Int,
    val idDoc: Int,
    val idPatient: Int,
    val durationTreatment: Date,
    val dateStartTreatment: Date,
    var medicamentList: List<Medicament>
)
