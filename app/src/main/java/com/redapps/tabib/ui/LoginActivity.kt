package com.redapps.tabib.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.clovertech.autolib.utils.PrefUtils
import com.google.gson.Gson
import com.redapps.tabib.databinding.ActivityLoginBinding
import com.redapps.tabib.model.User

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
            if (user.type == User.DOCTOR){
                startDoctorActivity()
            } else if (user.type == User.PATIENT) {
                startPatientActivity()
            }
        }

        // On login
        binding.buttonLogin.setOnClickListener(View.OnClickListener {
            val phone = binding.editPhoneLogin.text.toString()
            val password = binding.editPasswordLogin.toString()

            // temp
            startPatientActivity()
        })

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