package com.loice.assessment3.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.loice.assessment3.R
import com.loice.assessment3.databinding.ActivityAddBillBinding
import com.loice.assessment3.model.Bill
import com.loice.assessment3.utils.Constants

import com.loice.assessment3.viewmodel.BillsViewModel
import java.util.Calendar
import java.util.UUID
class AddBillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBillBinding
    private val billsViewModel: BillsViewModel by viewModels()
    var selectedDate=0
    var selectedMonth=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        setupFreqSpinner()
        binding.btnSaveBill.setOnClickListener {
            validateDill()
        }
    }
    private fun setupFreqSpinner() {
        val frequencies= arrayOf(Constants.WEEKLY,Constants.MONTHLY,Constants.ANNUAL)
        val freqAdapter = ArrayAdapter(this,  android.R.layout.simple_spinner_item,frequencies)
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spfrequency.adapter = freqAdapter
        binding.spfrequency.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (binding.spfrequency.selectedItem.toString()) {
                    Constants.WEEKLY -> {
                        showSpinner()
                       setupDueDateSpinner(Array(7) { it + 1 })
                    }

                    Constants.MONTHLY -> {
                        showSpinner()
                        setupDueDateSpinner(Array(31) { it + 1 })
                    }


                    Constants.ANNUAL -> {
                        showDatePicker()
                        setupDpDueDate()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    fun showSpinner(){
        binding.dpDueDateAnnual.hide()
        binding.spduedate.show()
    }
    fun showDatePicker(){
        binding.dpDueDateAnnual.show()
        binding.spduedate.hide()
    }
    private fun setupDueDateSpinner(dates:Array<Int>) {
        val duedateAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,dates)
        duedateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spduedate.adapter=duedateAdapter



    }
    fun setupDpDueDate(){
        val cal=Calendar.getInstance()
        binding.dpDueDateAnnual.init(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)){
            view,year,month,date->
            selectedDate=date
            selectedMonth=month+1
        }
    }

    fun validateDill(){
        val name=binding.etName.text.toString()
        val amount=binding.etName.text.toString()
        val frequency=binding.spfrequency.selectedItem.toString()
        var dueDate=Constants.empty_string
       if(frequency==Constants.ANNUAL) {
           dueDate="$selectedDate/$selectedMonth"
       }
        else{
            binding.spduedate.selectedItem.toString()
        }
        var error=false
        if(name.isBlank()){
            error=true
            binding.etName.error="please enter the bill name"
        }
        if(amount.isBlank()){
            error=true
            binding.etAmount.error="please enter the amount"
        }
        if(!error){
            val prefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
            val userId=prefs.getString(Constants.user_id,Constants.empty_string)
            val newBill=Bill(
               name = name,
                frequency = frequency,
                amount = amount.toDouble(),
                dueDate = dueDate,
                billId = UUID.randomUUID().toString(),
                userId = userId.toString()



            )
            billsViewModel.saveBill(newBill)
            clearForm()
            finish()
            navigateToSummaryFragment()
        }
    }

    fun clearForm(){
        binding.etName.setText(Constants.empty_string)
        binding.etAmount.setText(Constants.empty_string)
        binding.spfrequency.setSelection(0)
        showSpinner()
        binding.spduedate.setSelection(0)

    }

    private fun navigateToSummaryFragment() {
        val fragment = SummaryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

fun View.show(){
    this.visibility=View.VISIBLE
}
fun View.hide(){
    this.visibility=View.GONE
}








