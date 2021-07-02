package com.redapps.tabib.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.redapps.tabib.databinding.FragmentBookingBinding
import com.redapps.tabib.model.Doctor

class BookingFragment : Fragment() {

    private lateinit var bookingViewModel: BookingViewModel
    private var _binding: FragmentBookingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingViewModel =
            ViewModelProvider(this).get(BookingViewModel::class.java)

        _binding = FragmentBookingBinding.inflate(inflater, container, false)

        initDoctorRecycler()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDoctorRecycler(){
        val recycler = binding.recyclerDoctorBooking
        val adapter = DoctorAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
        adapter.setDoctors(getRandomDoctors(10))
    }

    private fun getRandomDoctors(count: Int): List<Doctor>{
        val list = mutableListOf<Doctor>()
        val doctor = Doctor("Ahmed", "Doctor", "", "05 35 54 23 88",
        "Psychology", 0.0, 0.0)
        for (i in 1..count){
            list.add(doctor)
        }
        return list
    }
}