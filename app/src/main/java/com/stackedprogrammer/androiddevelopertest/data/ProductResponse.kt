package com.stackedprogrammer.androiddevelopertest.data

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @field:SerializedName("summaries") val summaries: List<Product>
)