package com.redapps.tabib.viewmodel

import androidx.lifecycle.MutableLiveData
import com.redapps.tabib.model.Appointment
import com.redapps.tabib.model.Doctor
import com.redapps.tabib.network.DoctorApiClient
import com.redapps.tabib.network.PatientApiClient
import com.redapps.tabib.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientViewModel: BaseViewModel() {

    val appointments = MutableLiveData<List<Appointment>>()

    fun fetchAppointments(idPatient: Int){
        dataLoading.value = true
        PatientApiClient.instance.getAppointmentsByPatient(idPatient).enqueue(object : Callback<List<Appointment>> {
            override fun onResponse(call: Call<List<Appointment>>, response: Response<List<Appointment>>) {
                dataLoading.value = false
                if (response.isSuccessful){
                    appointments.apply {
                        value = response.body()!!
                    }
                    empty.value = response.body()!!.isEmpty()
                    failed.value = false
                } else {
                    toastMessage.value = "Error  : " + response.message()
                    failed.value = true
                    empty.value = false
                }
            }

            override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                toastMessage.value =  "Failed : " + t.message
                dataLoading.value =  false
                failed.value = true
                empty.value = false
            }
        })
    }
}