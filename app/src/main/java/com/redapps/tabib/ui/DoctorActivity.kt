package com.redapps.tabib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.redapps.tabib.R
import com.redapps.tabib.databinding.ActivityDoctorBinding
import com.redapps.tabib.utils.MenuUtils

class DoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageAccountMain.setOnClickListener {
            MenuUtils.showAccountDialog(this)
        }

        // temp
        Glide.with(this)
            .load(R.drawable.doctor1)
            .into(binding.imageAccountMain)


    }
}