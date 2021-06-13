package com.stackedprogrammer.androiddevelopertest.dagger

import com.stackedprogrammer.androiddevelopertest.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideProductService(): ProductService {
        return ProductService.create()
    }

}