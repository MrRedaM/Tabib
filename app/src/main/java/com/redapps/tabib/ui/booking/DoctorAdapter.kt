package com.redapps.tabib.ui.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redapps.tabib.R
import com.redapps.tabib.model.Doctor

class DoctorAdapter: RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    private val doctors = mutableListOf<Doctor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item_layout, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.name.text = doctor.lastName + " " + doctor.firstName
        holder.speciality.text = doctor.speciality
        holder.phone.text = doctor.phone
        Glide.with(holder.itemView.context)
            .load(R.drawable.doctor_harold)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    fun setDoctors(list: List<Doctor>){
        doctors.clear()
        doctors.addAll(list)
        notifyDataSetChanged()
    }

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var name = itemView.findViewById<TextView>(R.id.textNameBooking)
        var speciality = itemView.findViewById<TextView>(R.id.textSpecialityBooking)
        var location = itemView.findViewById<TextView>(R.id.textLocationBooking)
        var phone = itemView.findViewById<TextView>(R.id.textPhoneBooking)
        var image = itemView.findViewById<ImageView>(R.id.imageDoctorBooking)

    }
}