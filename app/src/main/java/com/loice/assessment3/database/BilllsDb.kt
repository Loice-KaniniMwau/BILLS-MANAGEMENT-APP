package com.loice.assessment3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.loice.assessment3.model.Bill

@Database(entities = [Bill::class], version = 1)
abstract class BilllsDb :RoomDatabase(){

    abstract fun billDao():BillDao
    companion object {
        var database:BilllsDb?=null

        fun getDatabase(context: Context):BilllsDb{
            if(database==null){
                database=Room
                    .databaseBuilder(context,BilllsDb::class.java,"BillsDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BilllsDb
        }
    }
}