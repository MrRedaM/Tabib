package com.redapps.tabib.ui.treatment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redapps.tabib.R
import com.redapps.tabib.model.Treatment

class TreatmentAdapter : RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder>() {

    private val treatments = mutableListOf<Treatment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.treatment_item_layout, parent, false)
        return TreatmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreatmentViewHolder, position: Int) {
        val treatment = treatments[position]
        val context = holder.itemView.context
        holder.textTitle.text = context.getString(R.string.treatment) + "#" + treatment.idTreatment
        Glide.with(context)
            .load(R.drawable.doctor_harold)
            .into(holder.imageDoc)
        holder.medicRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = MedicAdapter()
        adapter.setMedics(treatment.medicamentList)
        holder.medicRecycler.adapter = adapter
    }

    override fun getItemCount(): Int {
        return treatments.size
    }

    fun setTreatments(newTreatments: List<Treatment>){
        treatments.clear()
        treatments.addAll(newTreatments)
        notifyDataSetChanged()
    }

    class TreatmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle : TextView = view.findViewById(R.id.textTreatmentTitle)
        val textTime : TextView = view.findViewById(R.id.textTimeLeftTreatment)
        val textDocName : TextView = view.findViewById(R.id.textNameTreatment)
        val textDocSpe : TextView = view.findViewById(R.id.textSpecialityTreatment)
        val textDocPhone : TextView = view.findViewById(R.id.textPhoneTreatment)
        val imageDoc : ImageView = view.findViewById(R.id.imageDoctorTreatment)
        val medicRecycler : RecyclerView = view.findViewById(R.id.recyclerMedics)
    }
}