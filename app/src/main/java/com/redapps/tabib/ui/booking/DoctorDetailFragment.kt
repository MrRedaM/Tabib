package com.redapps.tabib.ui.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.redapps.tabib.R
import com.redapps.tabib.databinding.FragmentBookingBinding
import com.redapps.tabib.databinding.FragmentDoctorDetailBinding


class DoctorDetailFragment : Fragment() {

    private lateinit var bookingViewModel: BookingViewModel
    private var _binding: FragmentDoctorDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingViewModel =
            ViewModelProvider(this).get(BookingViewModel::class.java)

        _binding = FragmentDoctorDetailBinding.inflate(inflater, container, false)

        Glide.with(requireActivity())
            .load(R.drawable.doctor_harold)
            .into(binding.imageDoctorDetail)

        binding.buttonBackDoctorDetail.setOnClickListener {
            findNavController().navigate(R.id.action_nav_doctor_detail_to_navigation_booking)
        }

        return binding.root
    }

}