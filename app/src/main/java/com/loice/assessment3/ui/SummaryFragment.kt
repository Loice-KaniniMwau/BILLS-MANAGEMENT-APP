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
import com.loice.assessment3.viewmodel.BillsViewModel



class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null
    private lateinit var billsViewModel: BillsViewModel
    private lateinit var adapter: BillAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billsViewModel = ViewModelProvider(requireActivity()).get(BillsViewModel::class.java)
        adapter = BillAdapter(requireContext(), R.layout.item_bill, mutableListOf())
        binding?.billListView?.adapter = adapter
        billsViewModel.getAllBills().observe(viewLifecycleOwner, Observer { bills ->
            adapter.clear()
            adapter.addAll(bills)
            adapter.notifyDataSetChanged()
        })
        binding?.addBillButton?.setOnClickListener {
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
        binding?.addBillButton?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}