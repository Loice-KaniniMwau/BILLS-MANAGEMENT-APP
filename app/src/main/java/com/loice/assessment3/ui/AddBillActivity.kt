package com.loice.assessment3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.loice.assessment3.R
import com.loice.assessment3.databinding.ActivityAddBillBinding
import com.loice.assessment3.model.Bill

import com.loice.assessment3.viewmodel.BillsViewModel
import java.util.UUID
class AddBillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBillBinding
    private val billsViewModel: BillsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFreqSpinner()
        setupDueDateSpinner()
        binding.btnSaveBill.setOnClickListener {
            val selectedFrequency = binding.spFrequency.selectedItem.toString()
            val billName = binding.etName.text.toString()
            val billAmount = binding.etAmount.text.toString().toDouble()
            val selectedDueDate: String = when (selectedFrequency) {
                "Annual" -> {
                    val datePicker = binding.dpDueDateAnnual
                    "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"
                }
                else -> binding.spDueDate.selectedItem.toString()
            }
            val bill = Bill(
                billId = UUID.randomUUID().toString(),
                name = billName,
                amount = billAmount,
                frequency = selectedFrequency,
                dueDate = selectedDueDate,
                userId = "USER_ID"
            )
            billsViewModel.saveBill(bill)
            finish()
            navigateToSummaryFragment()
        }
    }
    private fun setupFreqSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.frequencies, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = adapter
    }
    private fun setupDueDateSpinner() {
        binding.spFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFrequency = binding.spFrequency.selectedItem.toString()
                if (selectedFrequency == "Annual") {
                    binding.spDueDate.visibility = View.GONE
                    binding.dpDueDateAnnual.visibility = View.VISIBLE
                } else {
                    binding.dpDueDateAnnual.visibility = View.GONE
                    binding.spDueDate.visibility = View.VISIBLE
                    val dueDateAdapter = when (selectedFrequency) {
                        "Monthly" -> {
                            val daysInMonth = 1..31
                            ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, daysInMonth.toList())
                        }
                        "Quarterly" -> {
                            val daysInQuarter = 1..90
                            ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, daysInQuarter.toList())
                        }
                        else -> {
                            ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, arrayOf(1, 2, 3, 4, 5, 6, 7))
                        }
                    }
                    dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spDueDate.adapter = dueDateAdapter
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun navigateToSummaryFragment() {
        val fragment = SummaryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}







