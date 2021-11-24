package com.assignment.bitcoinrate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.assignment.bitcoinrate.background.DownloadBitCoinRate
import com.assignment.bitcoinrate.data.BitCoinDetails
import com.assignment.bitcoinrate.data.BitCoinRepository
import java.util.concurrent.TimeUnit

const val TAG_OUTPUT = "TAG_OUTPUT"

class BitCoinRateViewModel(
    private val workManager: WorkManager,
    repository: BitCoinRepository
) : ViewModel() {

    val bitCoinDetails: LiveData<BitCoinDetails> = repository.bitCoinDetails.asLiveData()

    fun getBitCoinRate() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val work = PeriodicWorkRequestBuilder<DownloadBitCoinRate>(2, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()
        workManager.enqueue(work)
    }

}