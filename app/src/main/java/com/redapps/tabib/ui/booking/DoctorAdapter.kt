package com.redapps.tabib.ui.booking

import android.content.Context
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
            .load(if (position % 2 == 0) R.drawable.doctor1 else R.drawable.doctor2)
            .into(holder.image)
        holder.itemView.setOnClickListener(View.OnClickListener {
            val context = holder.itemView.context
            val extras = FragmentNavigatorExtras(
                    holder.image to prepareTransition(context, holder.image, R.string.image_transition_name)
                    ,holder.name to prepareTransition(context, holder.name, R.string.name_transition_name)
                    ,holder.speciality to prepareTransition(context, holder.speciality, R.string.speciality_transition_name)
                    ,holder.location to prepareTransition(context, holder.location, R.string.location_transition_name)
                    ,holder.phone to prepareTransition(context, holder.phone, R.string.phone_transition_name)
                    ,holder.locationImage to prepareTransition(context, holder.locationImage, R.string.location_image_transition_name)
                    ,holder.phoneImage to prepareTransition(context, holder.phoneImage, R.string.phone_image_transition_name))
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

    private fun prepareTransition(context: Context, view: View, id: Int): String{
        val name = context.getString(id)
        ViewCompat.setTransitionName(view, name)
        return name
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