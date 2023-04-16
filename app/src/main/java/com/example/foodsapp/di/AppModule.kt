package com.example.foodsapp.di

import com.example.foodsapp.service.AuthService
import com.example.foodsapp.data.datasource.CartDatasource
import com.example.foodsapp.data.datasource.FoodItemDatasource
import com.example.foodsapp.data.repo.CartRepo
import com.example.foodsapp.data.repo.FoodItemRepo
import com.example.foodsapp.retrofit.ApiUtils
import com.example.foodsapp.retrofit.dao.CartDAO
import com.example.foodsapp.retrofit.dao.FoodItemDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodDAO(): FoodItemDAO {
        return ApiUtils.getFoodDAO()
    }

    @Provides
    @Singleton
    fun provideFoodItemDatasource(foodItemDAO: FoodItemDAO): FoodItemDatasource {
        return FoodItemDatasource(foodItemDAO)
    }

    @Provides
    @Singleton
    fun provideFoodItemRepo(foodItemDatasource: FoodItemDatasource): FoodItemRepo {
        return FoodItemRepo(foodItemDatasource)
    }

    @Provides
    @Singleton
    fun provideCartDAO(): CartDAO {
        return ApiUtils.getCartDAO()
    }

    @Provides
    @Singleton
    fun provideCartDatasource(cartDAO: CartDAO): CartDatasource {
        return CartDatasource(cartDAO)
    }

    @Provides
    @Singleton
    fun provideCartRepo(cartDatasource: CartDatasource): CartRepo {
        return CartRepo(cartDatasource)
    }

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return AuthService()
    }

}