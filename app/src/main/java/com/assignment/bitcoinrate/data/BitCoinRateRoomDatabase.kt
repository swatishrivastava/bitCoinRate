package com.assignment.bitcoinrate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BitCoinDetails::class), version = 1, exportSchema = false)
abstract class BitCoinRateRoomDatabase : RoomDatabase() {

    abstract fun bitCoinDao(): BitCoinDao

    companion object {
        @Volatile
        private var INSTANCE: BitCoinRateRoomDatabase? = null
        fun getDatabase(context: Context): BitCoinRateRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BitCoinRateRoomDatabase::class.java,
                    "bitCoin_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}