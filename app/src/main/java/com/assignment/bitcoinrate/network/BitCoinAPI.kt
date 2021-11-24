package com.assignment.bitcoinrate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.assignment.bitcoinrate.domain.BitCoinData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_BITCOIN_URL =
    "https://api.coindesk.com/v1/bpi/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_BITCOIN_URL)
    .build()


interface BitCoinAPIService {
    @GET("currentprice.json/")
    suspend fun getBitCoinData(): BitCoinData
}

object BitCoinAPI {
    val RETROFIT_API: BitCoinAPIService by lazy { retrofit.create(BitCoinAPIService::class.java) }
}