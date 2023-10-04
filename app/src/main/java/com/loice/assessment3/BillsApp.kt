package com.loice.assessment3

import android.app.Application
import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.loice.assessment3.utils.Constants
import com.loice.assessment3.workmanager.DataSyncWorker
import com.loice.assessment3.workmanager.UpcomingBillsWorker
import java.util.concurrent.TimeUnit

class BillsApp:Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext

        val periodicWorkRequest= PeriodicWorkRequestBuilder<UpcomingBillsWorker>(15,TimeUnit.MINUTES)
            .addTag(Constants.CREATE_RECURRING_BILLS)
            .build()
        WorkManager.getInstance(appContext)
            .enqueueUniquePeriodicWork(Constants.CREATE_RECURRING_BILLS,ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest)

//        constraints
        val constraints=Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val syncPeriodicWorkRequest= PeriodicWorkRequestBuilder<DataSyncWorker>(1,TimeUnit.HOURS)
            .addTag("SYNC_BILLS")
            .setConstraints(constraints)

            .build()
        WorkManager.getInstance(appContext)
            .enqueueUniquePeriodicWork("SYNC_BILLS",ExistingPeriodicWorkPolicy.KEEP,syncPeriodicWorkRequest)


    }



}