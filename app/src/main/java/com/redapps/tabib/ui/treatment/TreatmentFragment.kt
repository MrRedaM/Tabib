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
import com.clovertech.autolib.utils.PrefUtils
import com.google.gson.Gson
import com.redapps.tabib.databinding.FragmentTreatmentBinding
import com.redapps.tabib.model.Medicament
import com.redapps.tabib.model.Treatment
import com.redapps.tabib.model.User
import com.redapps.tabib.utils.ToastUtils
import com.redapps.tabib.viewmodel.PatientViewModel
import java.util.*
import kotlin.math.abs

class TreatmentFragment : Fragment() {

    private lateinit var vm: PatientViewModel
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
        vm =
            ViewModelProvider(this).get(PatientViewModel::class.java)

        _binding = FragmentTreatmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userJson = PrefUtils.with(requireContext()).getString(PrefUtils.Keys.USER, "")
        val user = Gson().fromJson(userJson, User::class.java)

        initTreatmentsPager()
        setupObservers()
        binding.root.setOnRefreshListener {
            vm.fetchTreatments(user.id)
        }
        vm.fetchTreatments(user.id)

    }

    private fun setupObservers(){
        vm.treatments.observeForever {
            updateTreatments(it)
        }
        vm.dataLoading.observeForever {
            binding.root.isRefreshing = it
        }
        vm.empty.observeForever {
            when (it) {
                true -> binding.emptyLayout.visibility = View.VISIBLE
                false -> binding.emptyLayout.visibility = View.GONE
            }
        }
        vm.failed.observeForever {
            /*when (it) {
                true -> binding.layoutFailed.visibility = View.VISIBLE
                false -> binding.layoutFailed.visibility = View.GONE
            }*/
        }
        vm.toastMessage.observeForever {
            ToastUtils.longToast(requireContext(), it)
        }
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
        treatmentAdapter.setTreatments(newTreatments)
        binding.textTreatmentNumber.text = newTreatments.size.toString()
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