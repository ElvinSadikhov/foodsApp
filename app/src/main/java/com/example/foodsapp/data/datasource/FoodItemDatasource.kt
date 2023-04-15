package com.example.foodsapp.data.datasource

import com.example.foodsapp.data.entity.FoodItem
import com.example.foodsapp.retrofit.dao.FoodItemDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodItemDatasource(var foodItemDAO: FoodItemDAO) {

    suspend fun loadFoodItems(): List<FoodItem> =
        withContext(Dispatchers.IO) {
            foodItemDAO.loadFoodItems().foods
        }

}