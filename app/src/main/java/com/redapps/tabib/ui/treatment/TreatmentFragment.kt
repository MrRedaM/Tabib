package com.redapps.tabib.ui.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.redapps.tabib.databinding.FragmentTreatmentBinding
import com.redapps.tabib.model.Medicament
import com.redapps.tabib.model.Treatment
import java.util.*
import kotlin.math.abs

class TreatmentFragment : Fragment() {

    private lateinit var treatmentViewModel: TreatmentViewModel
    private var _binding: FragmentTreatmentBinding? = null

    private val treatmentAdapter = TreatmentAdapter()

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

        initTreatmentsPager()

        // temp
        updateTreatments(getRandomTreatments(0))
        binding.root.setOnRefreshListener {
            updateTreatments(getRandomTreatments(4))
            binding.root.isRefreshing = false
        }

        return binding.root
    }

    private fun initTreatmentsPager() {
        val pager = binding.pagerTreatment
        pager.adapter = treatmentAdapter
        pager.clipToPadding = false
        pager.clipChildren = false
        pager.offscreenPageLimit = 2
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(4))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        pager.setPageTransformer(transformer)
        pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // update medics
            }
        })
    }

    private fun updateTreatments(newTreatments: List<Treatment>){
        if (newTreatments.isNotEmpty()){
            binding.emptyLayout.visibility = View.GONE
        } else {
            binding.emptyLayout.visibility = View.VISIBLE
        }
        treatmentAdapter.setTreatments(newTreatments)
        binding.textTreatmentNumber.text = newTreatments.size.toString()
    }

    private fun getRandomTreatments(count: Int): List<Treatment>{
        val list = mutableListOf<Treatment>()
        for (i in 1..count){
            val treatment = Treatment(i, 1, 1, Calendar.getInstance().time,
                Calendar.getInstance().time, getRandomMedics(5))
            list.add(treatment)
        }
        return list
    }

    private fun getRandomMedics(count: Int): List<Medicament> {
        val list = mutableListOf<Medicament>()
        val medic = Medicament(1, "Doliprane", 2)
        val medic1 = Medicament(2, "Efferalgan", 2)
        for (i in 1..count){
            if (i % 2 == 0) list.add(medic) else list.add(medic1)
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}