package com.redapps.tabib.ui.treatment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.redapps.tabib.R
import com.redapps.tabib.databinding.AdviceDialogLayoutBinding
import com.redapps.tabib.model.Advice
import com.redapps.tabib.model.Appointment
import com.redapps.tabib.model.Message
import com.redapps.tabib.model.Treatment
import com.redapps.tabib.network.PatientApiClient
import com.redapps.tabib.utils.ToastUtils
import com.redapps.tabib.utils.UserUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TreatmentAdapter : RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder>() {

    private val treatments = mutableListOf<Treatment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.treatment_item_layout, parent, false)
        return TreatmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreatmentViewHolder, position: Int) {
        val treatment = treatments[position]
        val context = holder.itemView.context
        holder.textTitle.text = context.getString(R.string.treatment) + " " + treatment.idTreatment
        Glide.with(context)
            .load(if (position % 2 == 0) R.drawable.doctor1 else R.drawable.doctor2)
            .into(holder.imageDoc)
        holder.medicRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = MedicAdapter()
        adapter.setMedics(treatment.medicamentList)
        holder.medicRecycler.adapter = adapter
        holder.buttonAdvice.setOnClickListener {
            showAdviceDialog(context, treatment)
        }
    }

    private fun showAdviceDialog(context: Context, treatment: Treatment) {
        val dialog = BottomSheetDialog(context)
        val binding = AdviceDialogLayoutBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        binding.buttonSendAdvice.setOnClickListener {
            // Send Advice
            val message = binding.editAdvice.text.toString()
            val user = UserUtils.getCurrentUser(context)
            PatientApiClient.instance.sendAdvice(Advice(user.id, treatment.idDoc, message)).enqueue(object :
                Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    if (response.isSuccessful){
                        ToastUtils.longToast(context, "Advice sent!")
                        dialog.dismiss()
                    } else {
                        ToastUtils.longToast(context, "Error : " + response.message())
                    }
                }

                override fun onFailure(call: Call<Message>, t: Throwable) {
                    ToastUtils.longToast(context, "Failed : " + t.message)
                    //dialog.dismiss()
                }
            })
        }

        dialog.show()
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
        val buttonAdvice : Button = view.findViewById(R.id.buttonAdvice)
    }
}