package com.redapps.tabib.ui.appointment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.redapps.tabib.R
import com.redapps.tabib.model.Appointment
import net.glxn.qrgen.android.QRCode


class AppointmentAdapter(val fragment: AppointmentFragment) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>(){

    private val appointments = mutableListOf<Appointment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_item_layout, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val context = holder.itemView.context
        val appointment = appointments[position]
        Glide.with(context)
            .load(if (position % 2 == 0) R.drawable.doctor1 else R.drawable.doctor2)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            showQrDialog(fragment, appointment.idApt)
        }
    }

    override fun getItemCount(): Int {
        return appointments.size
    }

    fun setAppointments(newAppointments: List<Appointment>){
        appointments.clear()
        appointments.addAll(newAppointments)
        notifyDataSetChanged()
    }

    private fun showQrDialog(fragment: Fragment, appointmentId: Int){
        val dialog = BottomSheetDialog(fragment.requireContext())
        val view = fragment.layoutInflater.inflate(R.layout.qr_dialog_layout, null)
        dialog.setContentView(view)

        val qrImage = view.findViewById<ImageView>(R.id.imageQrCode)
            Glide.with(fragment)
                .load(QRCode.from(appointmentId.toString()).bitmap())
                .into(qrImage)

        //val multiFormatWriter = MultiFormatWriter()

        //try {
        //    val bitMatrix = multiFormatWriter.encode(
        //        appointmentId.toString(),
        //        BarcodeFormat.QR_CODE,
        //        500,
        //        500
        //    )
        //    val barcodeEncoder = BarcodeEncoder()
        //    val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
        //    val qrImage = view.findViewById<ImageView>(R.id.imageQrCode)
        //    Glide.with(fragment)
        //        .load(bitmap)
        //        .into(qrImage)
        //} catch (e: Exception) {
        //    e.printStackTrace()
        //}

        dialog.show()
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