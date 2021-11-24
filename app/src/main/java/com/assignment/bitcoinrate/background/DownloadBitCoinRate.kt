package com.assignment.bitcoinrate.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.assignment.bitcoinrate.data.BitCoinRateRoomDatabase
import com.assignment.bitcoinrate.network.BitCoinAPI
import kotlin.random.Random


class DownloadBitCoinRate(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {
    private val TAG = DownloadBitCoinRate::class.java.name
    private val database by lazy { BitCoinRateRoomDatabase.getDatabase(appContext) }

    override suspend fun doWork(): Result {
        val retrofit = BitCoinAPI.RETROFIT_API
        return try {
            var response = retrofit.getBitCoinData()
            Log.d(TAG, "Data received")
            var data = com.assignment.bitcoinrate.data.BitCoinDetails(
                Random.hashCode(),
                response.bpi.USD.rate, response.bpi.USD.description
            )
            database.bitCoinDao().deleteAll()
            database.bitCoinDao().insert(data)
            Result.success()
        } catch (ex: Throwable) {
            Result.failure()
        }

    }
}