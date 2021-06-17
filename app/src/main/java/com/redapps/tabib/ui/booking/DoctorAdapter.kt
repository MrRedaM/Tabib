package com.redapps.tabib.ui.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
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
        holder.itemView.setOnClickListener(View.OnClickListener {
            val imageTransitionName = holder.itemView.context.getString(R.string.image_transition_name)
            val nameTransitionName = holder.itemView.context.getString(R.string.name_transition_name)
            val specialityTransitionName = holder.itemView.context.getString(R.string.speciality_transition_name)
            val locationTransitionName = holder.itemView.context.getString(R.string.location_transition_name)
            val phoneTransitionName = holder.itemView.context.getString(R.string.phone_transition_name)
            val phoneImageTransitionName = holder.itemView.context.getString(R.string.phone_image_transition_name)
            val locationImageTransitionName = holder.itemView.context.getString(R.string.location_image_transition_name)
            ViewCompat.setTransitionName(holder.name, nameTransitionName)
            ViewCompat.setTransitionName(holder.image, imageTransitionName)
            ViewCompat.setTransitionName(holder.speciality, specialityTransitionName)
            ViewCompat.setTransitionName(holder.location, locationTransitionName)
            ViewCompat.setTransitionName(holder.phone, phoneTransitionName)
            ViewCompat.setTransitionName(holder.locationImage, locationImageTransitionName)
            ViewCompat.setTransitionName(holder.phoneImage, phoneImageTransitionName)
            val extras = FragmentNavigatorExtras(holder.image to imageTransitionName
                    ,holder.name to nameTransitionName
                    ,holder.speciality to specialityTransitionName
                    ,holder.location to locationTransitionName
                    ,holder.phone to phoneTransitionName
                    ,holder.locationImage to locationImageTransitionName
                    ,holder.phoneImage to phoneImageTransitionName)
            it.findNavController().navigate(R.id.action_doctor_detail, null, null, extras)
        })
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
        var locationImage = itemView.findViewById<ImageView>(R.id.imageLocation)
        var phoneImage = itemView.findViewById<ImageView>(R.id.imagePhone)

    }
}