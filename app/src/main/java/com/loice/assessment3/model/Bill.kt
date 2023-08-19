package com.loice.assessment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.temporal.TemporalAmount

@Entity(tableName = "Bills")

data class Bill(
    @PrimaryKey var billId:String,
    var name:String,
    var amount: Double,
    var frequency:String,
    var dueDate:String,
    var userId:String,



)
