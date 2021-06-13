package com.stackedprogrammer.androiddevelopertest.data

import com.stackedprogrammer.androiddevelopertest.api.ProductService
import io.reactivex.Observable
import javax.inject.Inject

class ProductRepository @Inject constructor(private val service: ProductService) {

    fun getProducts() : Observable<ProductResponse> {
        return service.getProducts()
    }

}