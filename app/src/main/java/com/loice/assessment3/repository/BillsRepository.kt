package com.loice.assessment3.repository

import androidx.lifecycle.LiveData
import com.loice.assessment3.BillsApp
import com.loice.assessment3.database.BilllsDb
import com.loice.assessment3.database.BilllsDb.Companion.database
import com.loice.assessment3.model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    val db=BilllsDb.getDatabase(BillsApp.appContext)
    val billDao=db.billDao()

   suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            billDao.saveBill(bill)
        }
    }


    fun getAllBils(): LiveData<List<Bill>> {
        return  billDao.getAllBills()
    }
//    fun getBillById(billId:Int): LiveData<Bill>? {
//        return database?.billDao()?.getBillById(billId)
//    }
//    suspend fun deleteBillById(bill: Bill) {
//        withContext(Dispatchers.IO) {
//            database?.billDao()?.deleteBillById(bill)
//        }
//    }
}
//switching the context of the coroutine to the io thread ..why suspend fun(a long running operation which should not disrupt the io thread
//the repository is used to get  access the database
//class ContactsRepository {
//   private val database=ContactsDB.getDatabase(MyContactsApp.appContext)
//
//   suspend fun saveContact(contact:Contacts_Data){
//        withContext(Dispatchers.IO){
//            database.getContactDao().insertContact(contact)
//
//        }
//    }
//
//    fun getAllContacts():LiveData<List<Contacts_Data>>{
//        return  database.getContactDao().getAllContacts()
//    }