package com.loice.assessment3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loice.assessment3.model.Bill
import com.loice.assessment3.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel :ViewModel(){
    var billsRepository=BillsRepository()

    suspend fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsRepository.saveBill(bill)
        }
    }
}