package com.loice.assessment3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loice.assessment3.model.Bill

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBill(bill:Bill)

    @Query("SELECT * FROM bills ORDER BY name")
    fun getAllBills(): LiveData<List<Bill>>

    @Query("SELECT * FROM Bills WHERE  billId=:billId")
    fun getBillById(billId:Int):LiveData<Bill>
//    @Delete
//    suspend fun deleteBillById(bill: Bill)
}


