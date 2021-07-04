package com.redapps.tabib.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.clovertech.autolib.utils.PrefUtils
import com.google.gson.Gson
import com.redapps.tabib.databinding.ActivityLoginBinding
import com.redapps.tabib.model.User
import com.redapps.tabib.model.UserLogin
import com.redapps.tabib.network.AuthApiClient
import com.redapps.tabib.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get user from prefs
        val userJson = PrefUtils.with(this).getString(PrefUtils.Keys.USER, "")
        if (userJson != ""){
            // already logged in
            val gson = Gson()
            val user : User = gson.fromJson(userJson, User::class.java)
            if (user.docSpeciality != ""){
                startDoctorActivity()
            } else  {
                startPatientActivity()
            }
        }

        // On login
        binding.buttonLogin.setOnClickListener {
            val phone = binding.editPhoneLogin.text.toString()
            val password = binding.editPasswordLogin.text.toString()

            /*AuthApiClient.instance.login(phone, password).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful){
                        ToastUtils.longToast(applicationContext, response.body())
                    } else {
                        ToastUtils.longToast(applicationContext, "Error : " + response.message())
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    ToastUtils.longToast(applicationContext, "Failed : " + t.message)
                }

            })*/

            // temp
            startPatientActivity()
        }

        // temp
        binding.textForgotPass.setOnClickListener {
            startDoctorActivity()
        }
    }

    private fun startPatientActivity(){
        startActivity(Intent(this, PatientActivity::class.java))
        finish()
    }

    private fun startDoctorActivity(){
        startActivity(Intent(this, DoctorActivity::class.java))
        finish()
    }


}