package com.redapps.tabib.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.redapps.tabib.databinding.FragmentBookingBinding
import com.redapps.tabib.model.Doctor
import com.redapps.tabib.network.DoctorApiClient
import com.redapps.tabib.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        setupSearch()

        // temp
        updateDoctors(getRandomDoctors(0))
        binding.root.setOnRefreshListener {
            updateDoctors(getRandomDoctors(10))
            binding.root.isRefreshing = false
        }
        DoctorApiClient.instance.getDoctors().enqueue(object : Callback<List<Doctor>>{
            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if (response.isSuccessful){
                    ToastUtils.longToast(requireContext(), "Get Docs success!")
                    updateDoctors(response.body()!!)
                } else {
                    ToastUtils.longToast(requireContext(), "Error  : " + response.message())
                }
            }

            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                ToastUtils.longToast(requireContext(), "Failed : " + t.message)
            }

        })

        return binding.root
    }

    private fun setupSearch() {
        binding.searchDoctorBooking.doOnTextChanged { text, start, before, count ->
            doctorAdapter.filterDoctors(text.toString())
        }
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
        "Psychology", 0.0, 0.0, "9", "16")
        val doctor1 = Doctor("Hadjer", "Doctor", "", "05 55 64 44 35",
            "Cardiology", 0.0, 0.0, "9", "16")
        for (i in 1..count){
            if (i % 2 == 1) list.add(doctor) else list.add(doctor1)
        }
        return list
    }
}