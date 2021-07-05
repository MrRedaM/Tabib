package com.redapps.tabib.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.redapps.tabib.cache.CacheDB
import com.redapps.tabib.model.Doctor
import com.redapps.tabib.model.Treatment
import com.redapps.tabib.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientRepository {

    @WorkerThread
    fun insertTreatment(context: Context, treatment: Treatment){
        CacheDB.getDatabase(context).treatmentDAO().insertTreatment(treatment)
    }

    @WorkerThread
    fun clearUserCache(context: Context){
        CacheDB.getDatabase(context).treatmentDAO().clearTreatments()
    }

    fun getTreatments(context: Context): LiveData<List<Treatment>> {
        return CacheDB.getDatabase(context).treatmentDAO().getAllTreatments().asLiveData()
    }

    companion object {
        private var INSTANCE: PatientRepository? = null
        fun getInstance() = INSTANCE
            ?: PatientRepository().also {
                INSTANCE = it
            }
    }

}