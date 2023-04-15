package com.example.foodsapp.data.repo

import com.example.foodsapp.data.datasource.FoodItemDatasource
import com.example.foodsapp.data.entity.FoodItem

class FoodItemRepo(var foodItemDatasource: FoodItemDatasource) {

    suspend fun loadFoodItems(): List<FoodItem> = foodItemDatasource.loadFoodItems()

}