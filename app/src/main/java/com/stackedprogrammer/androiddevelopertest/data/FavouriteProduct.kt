package com.stackedprogrammer.androiddevelopertest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_products")
data class FavouriteProduct(
    @PrimaryKey @ColumnInfo(name = "product_id") val productId: String,
    val name: String,
    val price: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)