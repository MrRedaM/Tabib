package com.redapps.tabib.ui.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.redapps.tabib.databinding.FragmentTreatmentBinding

class TreatmentFragment : Fragment() {

    private lateinit var treatmentViewModel: TreatmentViewModel
    private var _binding: FragmentTreatmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        treatmentViewModel =
            ViewModelProvider(this).get(TreatmentViewModel::class.java)

        _binding = FragmentTreatmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}