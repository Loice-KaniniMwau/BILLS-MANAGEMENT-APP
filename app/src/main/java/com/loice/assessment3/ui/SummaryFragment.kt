package com.loice.assessment3.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loice.assessment3.R
import com.loice.assessment3.databinding.FragmentSummaryBinding


class SummaryFragment : Fragment() {
  var binding: FragmentSummaryBinding?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSummaryBinding.inflate(inflater,container,false)
        return binding?.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onResume() {
        super.onResume()
        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(),AddBillActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }


}