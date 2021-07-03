package com.redapps.tabib.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.clovertech.autolib.utils.PrefUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.redapps.tabib.R
import com.redapps.tabib.databinding.ActivityMainBinding

class PatientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Top account image on click
        binding.imageAccountMain.setOnClickListener(View.OnClickListener {
            showAccountDialog()
        })

        // setups
        initNavigation()

        // temp
        Glide.with(this)
            .load(R.drawable.patient)
            .into(binding.imageAccountMain)
    }

    private fun initNavigation(){
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_booking, R.id.navigation_treatment, R.id.navigation_appointment
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(destination.id == R.id.nav_doctor_detail) {
                binding.textAppTitle.visibility = View.GONE
                binding.imageAccountMain.visibility = View.GONE
                binding.navView.visibility = View.GONE
            } else {
                binding.textAppTitle.visibility = View.VISIBLE
                binding.imageAccountMain.visibility = View.VISIBLE
                binding.navView.visibility = View.VISIBLE
            }
        }
    }

    private fun showAccountDialog(){
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.account_bottomsheet_layout, null)
        dialog.setContentView(view)

        val logoutButton = view.findViewById<TextView>(R.id.textLogout)
        val settingButton = view.findViewById<TextView>(R.id.textSettings)

        // Setup onClickListeners
        logoutButton.setOnClickListener {
            // Delete user from prefs
            PrefUtils.with(this).save(PrefUtils.Keys.USER, "")
            // Goto login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        settingButton.setOnClickListener {

        }

        dialog.show()
    }
}