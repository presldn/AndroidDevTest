package com.stackedprogrammer.androiddevelopertest.data

import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteProductRepository @Inject constructor(
    private val favouriteProductDao: FavouriteProductDao
) {

    fun getFavouriteProducts() = favouriteProductDao.getFavouriteProducts()

    fun addFavouriteProduct(
        productId: String,
        name: String,
        amount: String,
        urlTemplate: String
    ): Completable {
        val favouriteProduct = FavouriteProduct(productId, name, amount, urlTemplate)
        return favouriteProductDao.insertFavouriteProduct(favouriteProduct)
    }

    fun removeFavouriteProduct(favouriteProduct: FavouriteProduct) : Completable {
        return favouriteProductDao.deleteFavouriteProduct(favouriteProduct)
    }

}