package com.loice.assessment3.ui
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loice.assessment3.R
import com.loice.assessment3.databinding.FragmentSummaryBinding
import com.loice.assessment3.utils.Utils
import com.loice.assessment3.viewmodel.BillsViewModel



class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null
    private lateinit var billsViewModel: BillsViewModel
    private lateinit var adapter: BillAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(layoutInflater,container,false)
        return  binding?.root
    }
    override fun onResume() {
        super.onResume()
        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))
        }
        billsViewModel.getMonthlySummary()
        showMonthlySummary()
    }

    fun showMonthlySummary(){
        billsViewModel.summaryLiveData.observe(this){
            summary->binding?.tvPaidAmt?.text= Utils.formatCurrency(summary.paid)
            binding?.tvPendingAmt?.text= Utils.formatCurrency(summary.upcoming)
            binding?.tvTotalAmt?.text=Utils.formatCurrency(summary.total)
            binding?.tvOverdueAmt?.text=Utils.formatCurrency(summary.overdue)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}