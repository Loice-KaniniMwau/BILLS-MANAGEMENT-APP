package com.loice.assessment3.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.loice.assessment3.model.Bill
import com.loice.assessment3.repository.BillsRepository

class DataSyncWorker(context: Context,workerParams:WorkerParameters):CoroutineWorker(context,workerParams) {

    val billsRepository=BillsRepository()
    override suspend fun doWork(): Result {
       billsRepository.syncBills()
        billsRepository.syncUpcomingBills()
        return Result.success()
    }
}