package com.redapps.tabib.ui.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.redapps.tabib.R
import com.redapps.tabib.model.Booking
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class BookingAdapter : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    private val bookings = mutableListOf<Booking>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.booking_item_layout, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.date.text = booking.startDate.dateToString("dd-MMMM")
        holder.timeStart.text = booking.startDate.dateToString("hh:mm")
        holder.timeEnd.text = booking.endDate.dateToString("hh:mm")
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    fun setBookings(list: List<Booking>) {
        bookings.clear()
        bookings.addAll(list)
        notifyDataSetChanged()
    }

    private fun Date.dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }

    class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val date = itemView.findViewById<TextView>(R.id.textDateBooking)
        val location = itemView.findViewById<TextView>(R.id.textLocationBookingItem)
        val timeStart = itemView.findViewById<TextView>(R.id.textStartTimeBooking)
        val timeEnd = itemView.findViewById<TextView>(R.id.textEndTimeBooking)

    }

}