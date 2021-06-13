package com.stackedprogrammer.androiddevelopertest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteProductDao {

    @Query("SELECT * FROM favourite_products")
    fun getFavouriteProducts() : Flow<List<FavouriteProduct>>

    @Insert
    fun insertFavouriteProduct(favouriteProduct: FavouriteProduct) : Completable

    @Delete
    fun deleteFavouriteProduct(favouriteProduct: FavouriteProduct) : Completable
}