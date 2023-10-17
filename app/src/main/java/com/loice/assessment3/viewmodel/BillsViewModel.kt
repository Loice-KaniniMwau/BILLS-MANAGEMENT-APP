package com.loice.assessment3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loice.assessment3.model.Bill
import com.loice.assessment3.model.BillsSummary
import com.loice.assessment3.model.UpcomingBill
import com.loice.assessment3.repository.BillsRepository
import kotlinx.coroutines.launch


class BillsViewModel:ViewModel() {
    val billsRepo = BillsRepository()
    val summaryLiveData=MutableLiveData<BillsSummary>()

    fun saveBill(bill: Bill) {
        viewModelScope.launch {
            billsRepo.saveBill(bill)
        }
    }
//    insert upcoming bills

    fun insertUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch {
            billsRepo.insertUpcomingBill(upcomingBill)
        }
    }

    fun getAllBills(): LiveData<List<Bill>> {
        return billsRepo.getAllBills()
    }


    fun getUpcomingBillsByFrequency(freq: String): LiveData<List<UpcomingBill>> {
        return billsRepo.getUpcomingBillsByFrequency(freq)
    }
    fun updateUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch{
            billsRepo.updateUpcomingBill(upcomingBill)
        }
    }

    fun getPaidBills():LiveData<List<UpcomingBill>>{
        return billsRepo.getPaidBills()
    }

    fun fetchRemoteData(){
        viewModelScope.launch {
            billsRepo.fetchRemoteUpcomingBills()
            billsRepo.fetchRemoteBills()
        }
    }

    fun getMonthlySummary(){
        viewModelScope.launch {
        summaryLiveData.postValue(billsRepo.getMonthlySummary().value)
        }
    }


}