package com.redapps.tabib.viewmodel

import androidx.lifecycle.MutableLiveData
import com.redapps.tabib.model.Doctor
import com.redapps.tabib.network.DoctorApiClient
import com.redapps.tabib.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorViewModel: BaseViewModel() {

    val doctors = MutableLiveData<List<Doctor>>()

    fun fetchDoctors(){
        dataLoading.value = true
        DoctorApiClient.instance.getDoctors().enqueue(object : Callback<List<Doctor>> {
            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                dataLoading.value = false
                if (response.isSuccessful){
                    doctors.apply {
                        value = response.body()!!
                    }
                    empty.value = response.body()!!.isEmpty()
                    failed.value = false
                } else {
                    toastMessage.apply {
                        value = "Error  : " + response.message()
                    }
                    empty.value = false
                    failed.value = true
                }
            }

            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                dataLoading.value = false
                toastMessage.value = "Failed : " + t.message
                empty.value = false
                failed.value = true
            }
        })

    }
}