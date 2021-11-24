package com.assignment.bitcoinrate.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class BitCoinRepository(private val bitCoinDao: BitCoinDao) {

    val bitCoinDetails: Flow<BitCoinDetails> = bitCoinDao.getUpdatedRate()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bitCoinDetails: BitCoinDetails) {
        bitCoinDao.insert(bitCoinDetails)
    }
}