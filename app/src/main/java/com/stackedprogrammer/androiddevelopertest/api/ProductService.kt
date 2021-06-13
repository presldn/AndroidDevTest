package com.stackedprogrammer.androiddevelopertest.api

import com.stackedprogrammer.androiddevelopertest.data.ProductResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductService {

    @GET("NAP/GB/en/60/0/summaries?categoryIds=2")
    fun getProducts(): Observable<ProductResponse>

    companion object {
        private const val BASE_URL = "https://api.net-a-porter.com/"

        fun create(): ProductService {
            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductService::class.java)
        }

    }

}