package com.loice.assessment3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loice.assessment3.model.Bill
import com.loice.assessment3.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel :ViewModel(){
    var billsRepository=BillsRepository()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsRepository.saveBill(bill)
        }
    }

    fun getAllBills():LiveData<List<Bill>>{
        return billsRepository.getAllBils()
    }
//    fun getBillById(billId:Int): LiveData<Bill>? {
//        return billsRepository.getBillById(billId)
//    }
//    fun deleteBill(bill: Bill) {
//        viewModelScope.launch {
//            billsRepository.deleteBillById(bill)
//        }
//    }


}

//fun getContacts():LiveData<List<Contacts_Data>>{
//    return contactsRepository.getAllContacts()
//}