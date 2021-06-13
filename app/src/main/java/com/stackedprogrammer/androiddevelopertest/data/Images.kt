package com.stackedprogrammer.androiddevelopertest.data

import com.google.gson.annotations.SerializedName

data class Images(
    @field:SerializedName("shots")
    val shots: List<String>,
    @field:SerializedName("sizes")
    val sizes: List<String>,
    @field:SerializedName("mediaType")
    val mediaType: String,
    @field:SerializedName("urlTemplate")
    val urlTemplate: String
)
