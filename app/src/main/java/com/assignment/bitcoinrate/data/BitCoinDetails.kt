package com.assignment.bitcoinrate.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bitCoin_table")
class BitCoinDetails(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "rate") val rate: String,
    @ColumnInfo(name = "desc") val desc: String
) {
}