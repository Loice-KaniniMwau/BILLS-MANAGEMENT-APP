package com.loice.assessment3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.loice.assessment3.R
import com.loice.assessment3.databinding.FragmentUpcomingBillsBinding
import com.loice.assessment3.model.UpcomingBill
import com.loice.assessment3.utils.Constants
import com.loice.assessment3.viewmodel.BillsViewModel


class UpcomingBillsFragment : Fragment() ,OnClickBill{
private var binding:FragmentUpcomingBillsBinding?=null
   private val billsViewModel:BillsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingBillsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        getUpcomingBills()
    }
  private fun getUpcomingBills(){
        billsViewModel.getUpcomingBillsByFrequency(Constants.WEEKLY)
            .observe(this){weeklyBills->
                val adapter = UpcomingBillsAdapter(weeklyBills,this)
                binding?.rvweekly?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvweekly?.adapter = adapter
            }

        billsViewModel.getUpcomingBillsByFrequency(Constants.MONTHLY)
            .observe(this){monthlyBills->
                val adapter = UpcomingBillsAdapter(monthlyBills,this)
                binding?.rvmonthly?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvmonthly?.adapter=adapter
            }

        billsViewModel.getUpcomingBillsByFrequency(Constants.ANNUAL)
            .observe(this){annualBills->
                val adapter = UpcomingBillsAdapter(annualBills,this)
                binding?.rvannual?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvannual?.adapter=adapter
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCheckBoxMarked(upcomingbill: UpcomingBill) {
        upcomingbill.paid=!upcomingbill.paid
        upcomingbill.synced=false
        billsViewModel.updateUpcomingBill(upcomingbill)
    }







}