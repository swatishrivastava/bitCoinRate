package com.assignment.bitcoinrate.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BitCoinDao {
    @Query("SELECT * FROM bitCoin_table")
    fun getUpdatedRate(): Flow<BitCoinDetails>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(details: BitCoinDetails)

    @Query("DELETE FROM bitCoin_table")
    suspend fun deleteAll()
}