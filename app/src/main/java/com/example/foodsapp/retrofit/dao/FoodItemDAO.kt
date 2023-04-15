package com.example.foodsapp.retrofit.dao

import com.example.foodsapp.data.response.FoodItemListResponse
import retrofit2.http.GET

interface FoodItemDAO {

    @GET("foods/getAllFoods.php")
    suspend fun loadFoodItems(): FoodItemListResponse

}