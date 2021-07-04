package com.redapps.tabib.utils

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.clovertech.autolib.utils.PrefUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.redapps.tabib.R
import com.redapps.tabib.ui.LoginActivity
import com.redapps.tabib.ui.PatientActivity

object MenuUtils {

    fun showAccountDialog(activity: AppCompatActivity){
        val dialog = BottomSheetDialog(activity)
        val view = activity.layoutInflater.inflate(R.layout.account_bottomsheet_layout, null)
        dialog.setContentView(view)

        val logoutButton = view.findViewById<TextView>(R.id.textLogout)
        val settingButton = view.findViewById<TextView>(R.id.textSettings)

        // Setup onClickListeners
        logoutButton.setOnClickListener {
            // Delete user from prefs
            PrefUtils.with(activity).save(PrefUtils.Keys.USER, "")
            // Goto login activity
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        }
        settingButton.setOnClickListener {

        }

        // temp
        Glide.with(activity)
            .load(if (activity is PatientActivity) R.drawable.patient else R.drawable.doctor1)
            .into(view.findViewById(R.id.imageAccountMenu))

        dialog.show()
    }

}