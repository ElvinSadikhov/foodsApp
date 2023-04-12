package com.example.foodsapp.di

import com.example.foodsapp.retrofit.ApiUtils
import com.example.foodsapp.retrofit.dao.CartDAO
import com.example.foodsapp.retrofit.dao.FoodDAO
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
    @Singleton
    fun provideFoodDAO(): FoodDAO {
        return ApiUtils.getFoodDAO()
    }

//    @Provides
    @Singleton
    fun provideCartDAO(): CartDAO {
        return ApiUtils.getCartDAO()
    }

}