package com.loice.assessment3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.loice.assessment3.model.Bill
import com.loice.assessment3.model.UpcomingBill

@Database(entities = [Bill::class, UpcomingBill::class], version = 5)
abstract class BillDB:RoomDatabase() {
    abstract fun billsDao():BillsDao
    abstract fun upcomingBillsDao():UpcomingBillsDao

    companion object{
       private var database:BillDB?=null
        fun getDataBase(context: Context):BillDB{
            if (database==null){
                database= Room
                    .databaseBuilder(context,BillDB::class.java,"BillsDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillDB
        }

    }

}



