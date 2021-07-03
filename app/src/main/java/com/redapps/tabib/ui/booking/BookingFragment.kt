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

    private val doctorAdapter = DoctorAdapter()

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

        // tenp
        updateDoctors(getRandomDoctors(10))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateDoctors(newDoctors: List<Doctor>){
        if (newDoctors.isNotEmpty()){
            binding.emptyDoctors.visibility = View.GONE
        } else {
            binding.emptyDoctors.visibility = View.VISIBLE
        }
        doctorAdapter.setDoctors(newDoctors)
    }

    private fun initDoctorRecycler(){
        val recycler = binding.recyclerDoctorBooking
        recycler.adapter = doctorAdapter
        recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun getRandomDoctors(count: Int): List<Doctor>{
        val list = mutableListOf<Doctor>()
        val doctor = Doctor("Ahmed", "Doctor", "", "05 35 54 23 88",
        "Psychology", 0.0, 0.0)
        val doctor1 = Doctor("Hadjer", "Doctor", "", "05 55 64 44 35",
            "Cardiology", 0.0, 0.0)
        for (i in 1..count){
            if (i % 2 == 1) list.add(doctor) else list.add(doctor1)
        }
        return list
    }
}