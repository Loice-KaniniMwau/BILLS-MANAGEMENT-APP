package com.loice.assessment3.repository

import com.loice.assessment3.BillsApp
import com.loice.assessment3.database.BilllsDb
import com.loice.assessment3.model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    val db=BilllsDb.getDatabase(BillsApp.appContext)
    val billDao=db.billDao()

   suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            billDao.insertBill(bill)
        }
    }
}
//switching the context of the coroutine to the io thread ..why suspend fun(a long running operation which should not disrupt the io thread
//the repository is used to get  access the database