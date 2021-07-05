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
import java.text.SimpleDateFormat
import java.util.*


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
        holder.date.text = appointment.date.dateToString("dd MMMM, yyyy")
        holder.timeStart.text = appointment.date.dateToString("hh:mm")
        val cal = Calendar.getInstance()
        cal.time = appointment.date
        cal.set(Calendar.MINUTE, cal[Calendar.MINUTE] + 30)
        holder.timeEnd.text = cal.time.dateToString("hh:mm")
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

        dialog.show()
    }

    private fun Date.dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }

    private fun String.toDate(format: String): Date?{
        return SimpleDateFormat(format).parse(this)
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