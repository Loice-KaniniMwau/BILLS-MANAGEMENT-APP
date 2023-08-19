package com.loice.assessment3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.loice.assessment3.R
import com.loice.assessment3.databinding.ActivityAddBillBinding
import com.loice.assessment3.viewmodel.BillsViewModel

class AddBillActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    val billsViewModel:BillsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setUpFrequencySpinner()

    }

    fun setUpFrequencySpinner(){
        val adapter=ArrayAdapter.createFromResource(this,R.array.frequencies,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter=adapter

    }
    fun setUpDueDateSpinner(){
        val weeklyAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_item, arrayOf(1,2,3,4,5,6,7))
weeklyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}