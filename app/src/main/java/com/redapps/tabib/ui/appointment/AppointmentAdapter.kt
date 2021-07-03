package com.redapps.tabib.ui.appointment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redapps.tabib.R
import com.redapps.tabib.model.Appointment

class AppointmentAdapter : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>(){

    private val appointments = mutableListOf<Appointment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_item_layout, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val context = holder.itemView.context
        Glide.with(context)
            .load(if (position % 2 == 0) R.drawable.doctor1 else R.drawable.doctor2)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return appointments.size
    }

    fun setAppointments(newAppointments: List<Appointment>){
        appointments.clear()
        appointments.addAll(newAppointments)
        notifyDataSetChanged()
    }

    class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date : TextView = view.findViewById(R.id.textDateAppointment)
        val name : TextView = view.findViewById(R.id.textNameAppointment)
        val speciality : TextView = view.findViewById(R.id.textSpecialityAppointment)
        val location : TextView = view.findViewById(R.id.textLocationAppointment)
        val phone : TextView = view.findViewById(R.id.textPhoneAppointment)
        val timeStart : TextView = view.findViewById(R.id.textTimeStartAppointment)
        val timeEnd : TextView = view.findViewById(R.id.textTimeEndAppointment)
        val image : ImageView = view.findViewById(R.id.imageDoctorAppointment)

    }
}