package com.stackedprogrammer.androiddevelopertest.dagger

import android.content.Context
import com.stackedprogrammer.androiddevelopertest.data.AppDatabase
import com.stackedprogrammer.androiddevelopertest.data.FavouriteProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideFavouriteProductsDao(appDatabase: AppDatabase): FavouriteProductDao {
        return appDatabase.favouriteProductDao()
    }

}