package com.stackedprogrammer.androiddevelopertest.data

import com.google.gson.annotations.SerializedName


data class Product(
    @field:SerializedName("id")
    val productId: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("price")
    val price: Price,
    @field:SerializedName("images")
    val images: Images)
