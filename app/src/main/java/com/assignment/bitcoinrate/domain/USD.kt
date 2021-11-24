package com.assignment.bitcoinrate.domain

data class USD(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Double,
    val symbol: String
)