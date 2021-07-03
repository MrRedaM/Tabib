package com.redapps.tabib.viewmodel

import androidx.lifecycle.MutableLiveData
import com.redapps.tabib.model.Doctor

class DoctorViewModel: BaseViewModel() {

    val doctors = MutableLiveData<List<Doctor>>()

    fun fetchDoctors(){
        dataLoading.value = true

    }
}