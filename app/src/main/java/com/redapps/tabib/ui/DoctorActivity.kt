package com.redapps.tabib.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.redapps.tabib.R
import com.redapps.tabib.databinding.ActivityDoctorBinding
import com.redapps.tabib.utils.MenuUtils
import com.redapps.tabib.utils.ToastUtils


class DoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Account image click
        binding.imageAccountMain.setOnClickListener {
            MenuUtils.showAccountDialog(this)
        }

        // QR scan button click
        var scanLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                ToastUtils.longToast(this, "Appointment ID : " + result.data!!.getStringExtra("SCAN_RESULT"))
            }
        }
        binding.buttonScanQr.setOnClickListener {
            try {
                val intent = Intent("com.google.zxing.client.android.SCAN")
                intent.setPackage("com.google.zxing.client.android")
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE")
                scanLauncher.launch(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No QR scanner app found on your phone", Toast.LENGTH_LONG).show()
            }
        }

        // temp
        Glide.with(this)
            .load(R.drawable.doctor1)
            .into(binding.imageAccountMain)


    }
}