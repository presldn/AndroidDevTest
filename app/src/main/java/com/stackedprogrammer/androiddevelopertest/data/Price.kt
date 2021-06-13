package com.stackedprogrammer.androiddevelopertest.data

import com.google.gson.annotations.SerializedName

data class Price(
    @field:SerializedName("currency")
    val currency: String,
    @field:SerializedName("divisor")
    val divisor: String,
    @field:SerializedName("amount")
    val amount: String,
)
