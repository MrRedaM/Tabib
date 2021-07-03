package com.redapps.tabib.ui.appointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.redapps.tabib.databinding.FragmentAppointmentBinding
import com.redapps.tabib.model.Appointment

class AppointmentFragment : Fragment() {

    private lateinit var appointmentViewModel: AppointmentViewModel
    private var _binding: FragmentAppointmentBinding? = null

    val adapter = AppointmentAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appointmentViewModel =
            ViewModelProvider(this).get(AppointmentViewModel::class.java)

        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)

        initRecycler()

        // temp
        updateAppointments(getRandomAppointments(2))

        return binding.root
    }

    private fun updateAppointments(newAppointments: List<Appointment>){
        adapter.setAppointments(newAppointments)
    }

    private fun initRecycler() {
        binding.recyclerAppointment.adapter = adapter
        binding.recyclerAppointment.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getRandomAppointments(count: Int): List<Appointment>{
        val list = mutableListOf<Appointment>()
        for (i in 1..count){
            val appointment = Appointment(1, 1, 1, "")
            list.add(appointment)
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}